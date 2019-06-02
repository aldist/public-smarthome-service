package de.aldist.smarthomeappserver.controller;

import de.aldist.smarthomeappserver.dto.Command;
import de.aldist.smarthomeappserver.service.ServiceStarter;
import java.util.Date;
import java.util.Optional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class SmarthomeController {

  private final ServiceStarter serviceStarter;
  private Class desiredService;

  public SmarthomeController(ServiceStarter serviceStarter, Class desiredService) {
    this.serviceStarter = serviceStarter;
    this.desiredService = desiredService;
  }

  @GetMapping("/get")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> getStatus(
      @DateTimeFormat(pattern = "ddMMyyyy") Optional<Date> from,
      @DateTimeFormat(pattern = "ddMMyyyy") Optional<Date> to) {
    return new ResponseEntity<>(
        this.serviceStarter.getDataFromServices(this.desiredService, from, to),
        HttpStatus.OK
    );
  }

  @PostMapping("/set")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> setValue(
      @RequestParam String service,
      @RequestParam String device,
      @RequestParam String property,
      @RequestParam String value) {
    return new ResponseEntity<>(
        this.serviceStarter.executeCommandOnDevice(new Command(service, device, property, value)),
        HttpStatus.OK
    );
  }
}
