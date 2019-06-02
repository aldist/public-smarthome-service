package de.aldist.smarthomeappserver.dto.FHEMResponse;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetResults {

  @JsonAlias({"Name", "name"})
  private String name;

  //@JsonAlias({"PossibleSets", "possibleSets"})
  //private String possibleSets;

  @JsonAlias({"Readings", "readings"})
  private GetReadings getReadings;

  public GetReadings getGetReadings() {
    return this.getReadings;
  }

  public void setGetReadings(GetReadings getReadings) {
    this.getReadings = getReadings;
  }

  /*
    public String getPossibleSets() {
        return this.possibleSets;
    }

    public void setPossibleSets(String possibleSets) {
        this.possibleSets = possibleSets;
    }
*/
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Results{"
        + "name='" + this.name + "'"
        //+ ", possibleSets='" + this.possibleSets + "'"
        + ", readings=" + this.getReadings.toString()
        + "}";
  }
}
