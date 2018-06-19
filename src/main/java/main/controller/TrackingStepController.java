package main.controller;

import main.service.TrackingStepService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/step")
public class TrackingStepController {

    private TrackingStepService trackingStepService;

    public TrackingStepController(TrackingStepService trackingStepService) {
        this.trackingStepService = trackingStepService;
    }

}
