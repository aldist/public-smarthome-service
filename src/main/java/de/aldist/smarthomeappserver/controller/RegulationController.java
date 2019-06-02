package de.aldist.smarthomeappserver.controller;

import de.aldist.smarthomeappserver.service.ServiceStarter;
import de.aldist.smarthomeappserver.service.regulation.RegulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/regulation")
public class RegulationController extends SmarthomeController {

    @Autowired
    public RegulationController(ServiceStarter serviceStarter) {
        super(serviceStarter, RegulationService.class);
    }
}
