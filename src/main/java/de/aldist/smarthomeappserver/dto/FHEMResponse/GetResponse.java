package de.aldist.smarthomeappserver.dto.FHEMResponse;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetResponse {

  @JsonAlias({"Results", "results"})
  private List<GetResults> results;

  public List<GetResults> getResults() {
    return results;
  }

  public void setResults(List<GetResults> results) {
    this.results = results;
  }

  @Override
  public String toString() {
    return "Response{"
        + "results=[" + this.results.stream()
        .map(GetResults::toString)
        .reduce("", (acc, result) -> acc + result)
        + "]}";
  }
}
