package de.aldist.smarthomeappserver.utils;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class HttpServletResponder {

  private HttpServletResponse servletResponse;
  private String httpBody;

  private HttpServletResponder(HttpServletResponse servletResponse) {
    this.servletResponse = servletResponse;
  }

  public static HttpServletResponder HttpServletResponderBuilder(
      HttpServletResponse servletResponse) {
    return new HttpServletResponder(servletResponse);
  }

  public HttpServletResponder setHttpStatus(HttpStatus httpStatus) {
    this.servletResponse.setStatus(httpStatus.value());
    return this;
  }

  public HttpServletResponder setContentType(MediaType mediaType) {
    this.servletResponse.setContentType(mediaType.toString());
    return this;
  }

  public HttpServletResponder setJSONBody(Object body) {
    this.httpBody = new Gson().toJson(body);
    return this;
  }

  public void fireRequest() throws IOException {
    this.servletResponse.getWriter().write(this.httpBody);
    this.servletResponse.getWriter().flush();
    this.servletResponse.getWriter().close();
  }
}
