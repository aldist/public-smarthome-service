package de.aldist.smarthomeappserver.service.regulation;

import de.aldist.smarthomeappserver.dto.Command;
import de.aldist.smarthomeappserver.dto.FHEMResponse.GetResponse;
import de.aldist.smarthomeappserver.dto.FHEMResponse.GetResults;
import de.aldist.smarthomeappserver.dto.ServiceResponse;
import io.vavr.control.Either;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class FhemHeatingService implements RegulationService {

  @Value("${fhem.url}")
  private String fhemUrl;

  private String className = this.getClass().getSimpleName();
  private RestTemplate restTemplate;

  @Autowired
  public FhemHeatingService(RestTemplateBuilder builder) {
    this.restTemplate = builder
        //.setConnectTimeout(1337)
        //.setReadTimeout(1337)
        .build();
  }

  @Override
  public CompletableFuture<ServiceResponse> getData(Optional<Date> from, Optional<Date> to) {
    return CompletableFuture.supplyAsync(
        () -> getDesiredKindOfData(from, to).fold(
            left -> new ServiceResponse<>(this.className, left, true),
            right -> new ServiceResponse<>(this.className, right, false)
        )
    );
  }

  @Override
  public ServiceResponse<String> setValue(Command command) {
    return this.doHttpRequest(
        String.class,
        FHEM.METHOD_SET.getValue(),
        command.getDevice(),
        command.getProperty(),
        command.getValue()
    ).fold(
        left -> new ServiceResponse<>(this.className, left, true),
        right -> new ServiceResponse<>(this.className, right, false)
    );
  }

  private Either<String, ?> getDesiredKindOfData(Optional<Date> from, Optional<Date> to) {
    /* ToDo: implementing HistoryData
    if (from.isPresent() || to.isPresent()) {
      return this.getHistoryData(from, to);
    }
    */
    return this.getCurrentData();
  }

  // ToDo: no String as return object
  private Either<String, String> getHistoryData(Optional<Date> from, Optional<Date> to)
      throws RestClientException {
    return this.doHttpRequest(
        String.class,
        FHEM.METHOD_GET_LOG_DB.getValue(),
        from.orElseGet(() -> new Date(FHEM.OLDEST_DATE.getValue())).toString(),
        to.orElseGet(Date::new).toString(),
        FHEM.GET_ALL_DEVICES.getValue()
    );
  }

  private Either<String, List<GetResults>> getCurrentData() {
    return this.doHttpRequest(
        GetResponse.class,
        FHEM.METHOD_LIST.getValue(),
        FHEM.LIST_TYPE_THERMOSTAT.getValue()
    ).map(GetResponse::getResults);
  }

  // ToDo: own class for fhem http requests?
  private <T> Either<String, T> doHttpRequest(Class<T> responseClass, String... urlComponents) {
    T response;
    try {
      response = this.restTemplate.getForObject(
          this.fhemUrl + String.join(" ", urlComponents) + FHEM.NOHTML.getValue(),
          responseClass
      );
    } catch (RestClientException e) {
      //TODO: implement logger: ERROR: FhemHeatingService doHttpRequest + e.printStackTrace()
      return Either.left("Error: " + e.getMessage());
    }

    if (responseClass == String.class && response != null) {
      //TODO: implement logger: ERROR: FhemHeatingService doHttpRequest + response
      return Either.left("Error: " + response);
    }

    return Either.right(response);
  }
}
