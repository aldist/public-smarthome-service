package de.aldist.smarthomeappserver.service;

import de.aldist.smarthomeappserver.dto.Command;
import de.aldist.smarthomeappserver.dto.ServiceResponse;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ComponentService {

  public CompletableFuture<?> getData(Optional<Date> from, Optional<Date> to);

  public ServiceResponse<?> setValue(Command command);
}
