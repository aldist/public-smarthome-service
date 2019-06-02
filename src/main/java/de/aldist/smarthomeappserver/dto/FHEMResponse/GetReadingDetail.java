package de.aldist.smarthomeappserver.dto.FHEMResponse;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetReadingDetail {

    @JsonAlias({"Value", "value"})
    private String value;

    @JsonAlias({"Time", "time"})
    private String time;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ReadingDetail{"
                + "value='" + this.value + "'"
                + ", time='" + this.time + "'"
                + "}";
    }
}
