package de.aldist.smarthomeappserver.errorhandling;

public class ServiceStarterNoSuchServiceException extends RuntimeException {

  public ServiceStarterNoSuchServiceException(String message) {
    super(message);
  }
}
