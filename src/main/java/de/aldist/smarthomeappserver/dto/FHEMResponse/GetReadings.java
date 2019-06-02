package de.aldist.smarthomeappserver.dto.FHEMResponse;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetReadings {

  @JsonAlias({"RSSI", "rssi"})
  private GetReadingDetail rssi;

  @JsonAlias({"Battery", "battery"})
  private GetReadingDetail battery;

  @JsonAlias({"BatteryState", "batteryState"})
  private GetReadingDetail batteryState;

  @JsonAlias({"ComfortTemperature", "comfortTemperature"})
  private GetReadingDetail comfortTemperature;

  @JsonAlias({"DesiredTemperature", "desiredTemperature"})
  private GetReadingDetail desiredTemperature;

  @JsonAlias({"EcoTemperature", "ecoTemperature"})
  private GetReadingDetail ecoTemperature;

  @JsonAlias({"MaximumTemperature", "maximumTemperature"})
  private GetReadingDetail maximumTemperature;

  @JsonAlias({"MinimumTemperature", "minimumTemperature"})
  private GetReadingDetail minimumTemperature;

  @JsonAlias({"Mode", "mode"})
  private GetReadingDetail mode;

  @JsonAlias({"State", "state"})
  private GetReadingDetail state;

  @JsonAlias({"Temperature", "temperature"})
  private GetReadingDetail temperature;

  @JsonAlias({"WindowOpenDuration", "windowOpenDuration"})
  private GetReadingDetail windowOpenDuration;

  @JsonAlias({"WindowOpenTemperature", "windowOpenTemperature"})
  private GetReadingDetail windowOpenTemperature;

  public GetReadingDetail getRssi() {
    return rssi;
  }

  public void setRssi(GetReadingDetail rssi) {
    this.rssi = rssi;
  }

  public GetReadingDetail getBattery() {
    return battery;
  }

  public void setBattery(GetReadingDetail battery) {
    this.battery = battery;
  }

  public GetReadingDetail getBatteryState() {
    return batteryState;
  }

  public void setBatteryState(GetReadingDetail batteryState) {
    this.batteryState = batteryState;
  }

  public GetReadingDetail getComfortTemperature() {
    return comfortTemperature;
  }

  public void setComfortTemperature(GetReadingDetail comfortTemperature) {
    this.comfortTemperature = comfortTemperature;
  }

  public GetReadingDetail getDesiredTemperature() {
    return desiredTemperature;
  }

  public void setDesiredTemperature(GetReadingDetail desiredTemperature) {
    this.desiredTemperature = desiredTemperature;
  }

  public GetReadingDetail getEcoTemperature() {
    return ecoTemperature;
  }

  public void setEcoTemperature(GetReadingDetail ecoTemperature) {
    this.ecoTemperature = ecoTemperature;
  }

  public GetReadingDetail getMaximumTemperature() {
    return maximumTemperature;
  }

  public void setMaximumTemperature(GetReadingDetail maximumTemperature) {
    this.maximumTemperature = maximumTemperature;
  }

  public GetReadingDetail getMinimumTemperature() {
    return minimumTemperature;
  }

  public void setMinimumTemperature(GetReadingDetail minimumTemperature) {
    this.minimumTemperature = minimumTemperature;
  }

  public GetReadingDetail getMode() {
    return mode;
  }

  public void setMode(GetReadingDetail mode) {
    this.mode = mode;
  }

  public GetReadingDetail getState() {
    return state;
  }

  public void setState(GetReadingDetail state) {
    this.state = state;
  }

  public GetReadingDetail getTemperature() {
    return temperature;
  }

  public void setTemperature(GetReadingDetail temperature) {
    this.temperature = temperature;
  }

  public GetReadingDetail getWindowOpenDuration() {
    return windowOpenDuration;
  }

  public void setWindowOpenDuration(GetReadingDetail windowOpenDuration) {
    this.windowOpenDuration = windowOpenDuration;
  }

  public GetReadingDetail getWindowOpenTemperature() {
    return windowOpenTemperature;
  }

  public void setWindowOpenTemperature(GetReadingDetail windowOpenTemperature) {
    this.windowOpenTemperature = windowOpenTemperature;
  }

  @Override
  public String toString() {
    return "Readings{"
        + "rssi=" + this.rssi.toString()
        + ", battery=" + this.battery.toString()
        + ", batteryState=" + this.batteryState.toString()
        + ", comfortTemperature=" + this.comfortTemperature.toString()
        + ", desiredTemperature=" + this.desiredTemperature.toString()
        + ", ecoTemperature=" + this.ecoTemperature.toString()
        + ", maximumTemperature=" + this.maximumTemperature.toString()
        + ", minimumTemperature=" + this.minimumTemperature.toString()
        + ", mode=" + this.mode.toString()
        + ", state=" + this.state.toString()
        + ", temperature=" + this.temperature.toString()
        + ", windowOpenDuration=" + this.windowOpenDuration.toString()
        + ", windowOpenTemperature=" + this.windowOpenTemperature.toString()
        + "}";
  }
}
