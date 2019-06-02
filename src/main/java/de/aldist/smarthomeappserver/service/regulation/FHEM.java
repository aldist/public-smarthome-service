package de.aldist.smarthomeappserver.service.regulation;

public enum FHEM {

  NOHTML("&XHR=1"),

  OLDEST_DATE("2019-01-01"),

  METHOD_LIST("jsonlist2"),
  LIST_TYPE_THERMOSTAT("type=HeatingThermostat"),

  //METHOD_GET_LOG_DB("get logdb history -"),
  METHOD_GET_LOG_DB("get logdb history ALL"),
  GET_ALL_DEVICES("%:%"),

  METHOD_SET("set");

  private String keyword;

  FHEM(String keyword) {
    this.keyword = keyword;
  }

  public String getValue() {
    return keyword;
  }
}
