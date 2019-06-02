package de.aldist.smarthomeappserver.dto;

public class Command {

  private final String service;
  private final String device;
  private final String property;
  private final String value;

  public Command(String service, String device, String property, String value) {
    this.device = device;
    this.property = property;
    this.value = value;
    this.service = service;
  }

  public String getService() {
    return service;
  }

  public String getDevice() {
    return this.device;
  }

  public String getProperty() {
    return this.property;
  }

  public String getValue() {
    return this.value;
  }
}
