package de.aldist.smarthomeappserver.errorhandling;

import de.aldist.smarthomeappserver.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {ServiceStarterProcessingServicesException.class})
  protected ResponseEntity<Object> handleServiceStarterException(RuntimeException exception,
      WebRequest request) {
    return this.createErrorResponse(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {ServiceStarterNoSuchServiceException.class})
  protected ResponseEntity<Object> handleServiceStarterNoSuchServiceException(
      RuntimeException exception, WebRequest request) {
    return this.createErrorResponse(exception, request, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleAll(Exception exception, WebRequest request) {
    return this.createErrorResponse(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return this.createErrorResponse(exception, request, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
      HttpMediaTypeNotAcceptableException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return this.createErrorResponse(exception, request, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      HttpMediaTypeNotSupportedException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return this.createErrorResponse(exception, request, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(
      MissingPathVariableException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return this.createErrorResponse(exception, request, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return this.createErrorResponse(exception, request, HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<Object> createErrorResponse(Exception exception, WebRequest request,
      HttpStatus httpStatus) {
    ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), request.toString());
    return handleExceptionInternal(
        exception,
        errorResponse,
        new HttpHeaders(),
        httpStatus,
        request
    );
  }
}
