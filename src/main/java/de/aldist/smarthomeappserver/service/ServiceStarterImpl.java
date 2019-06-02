package de.aldist.smarthomeappserver.service;

import de.aldist.smarthomeappserver.dto.Command;
import de.aldist.smarthomeappserver.dto.ServiceResponse;
import de.aldist.smarthomeappserver.errorhandling.ServiceStarterNoSuchServiceException;
import de.aldist.smarthomeappserver.errorhandling.ServiceStarterProcessingServicesException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceStarterImpl implements ServiceStarter {

  private List<ComponentService> services;

  @Autowired
  public ServiceStarterImpl(List<ComponentService> services) {
    this.services = services;
  }

  @Override
  public List<?> getDataFromServices(Class desiredService, Optional<Date> from, Optional<Date> to) {
    try {
      return this.services.stream()
          .filter(desiredService::isInstance)
          .map(service -> service.getData(from, to))
          .map(CompletableFuture::join)
          .collect(Collectors.toList());
    } catch (CompletionException e) {
      //TODO: implement Logger: ERROR: ServiceStarter getDataFromServices Error + e.getMessage()
      throw new ServiceStarterProcessingServicesException(
          "Error: Problem occured while processing Services!");
    }
  }

  @Override
  public ServiceResponse<?> executeCommandOnDevice(Command command) {
    try {
      return this.services.stream()
          .filter(service -> this.isSameClass(service, command.getService()))
          .map(service -> service.setValue(command))
          .collect(Collectors.toList())
          .get(0);
    } catch (IndexOutOfBoundsException e) {
      //TODO: implement Logger: ERROR: ServiceStarter executeCommandDevice + e.printStackTrace()
      throw new ServiceStarterNoSuchServiceException("Error: No such service!");
    }
  }

  private boolean isSameClass(ComponentService service, String desiredService) {
    return service.getClass().getSimpleName().equals(desiredService);
  }
}
