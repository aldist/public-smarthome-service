package de.aldist.smarthomeappserver.service.sensors;

import de.aldist.smarthomeappserver.dto.Command;
import de.aldist.smarthomeappserver.dto.ServiceResponse;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;

@Service
public class WeatherService implements SensorsService {

  public WeatherService() {
  }

  @Override
  public CompletableFuture<?> getData(Optional<Date> from, Optional<Date> to) {
    return CompletableFuture.supplyAsync(() ->
        getWeatherData(
            from.orElseGet(Date::new),
            to.orElseGet(Date::new))
    );
  }

  @Override
  public ServiceResponse<?> setValue(Command command) {
    //TODO: set Command
    return new ServiceResponse<Object>(null, null, true);
  }

  private String getWeatherData(Date from, Date to) {
    return "Weather from " + from.toGMTString() + " to " + to.toGMTString();
  }
}
