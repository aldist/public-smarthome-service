package de.aldist.smarthomeappserver.controller;

import de.aldist.smarthomeappserver.service.ServiceStarter;
import de.aldist.smarthomeappserver.service.sensors.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
public class SensorsController extends SmarthomeController {

  @Autowired
  public SensorsController(ServiceStarter serviceStarter) {
    super(serviceStarter, SensorsService.class);
  }
}