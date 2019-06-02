package de.aldist.smarthomeappserver.config;

import static de.aldist.smarthomeappserver.utils.HttpServletResponder.HttpServletResponderBuilder;

import de.aldist.smarthomeappserver.dto.ErrorResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  /* ToDo: LOGGER
  public static final Logger LOG
          = Logger.getLogger(CustomAccessDeniedHandler.class);
*/
  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException exception) throws IOException {

    Authentication auth
        = SecurityContextHolder.getContext().getAuthentication();
    /*
      if (auth != null) {
           ToDo: LOGGER!
          LOG.warn("User: " + auth.getName()
                  + " attempted to access the protected URL: "
                  + request.getRequestURI());

      }
    */
    HttpServletResponderBuilder(response)
        .setHttpStatus(HttpStatus.FORBIDDEN)
        .setContentType(MediaType.APPLICATION_JSON_UTF8)
        .setJSONBody(new ErrorResponse(exception.getMessage(), (request).getRequestURI()))
        .fireRequest();
  }
}
