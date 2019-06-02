package de.aldist.smarthomeappserver.service;

import de.aldist.smarthomeappserver.dto.Command;
import de.aldist.smarthomeappserver.dto.ServiceResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ServiceStarter {

  public List<?> getDataFromServices(Class desiredService, Optional<Date> from, Optional<Date> to);

  public ServiceResponse<?> executeCommandOnDevice(Command command);
}