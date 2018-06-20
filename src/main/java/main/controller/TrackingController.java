package main.controller;

import main.core.rabbit.RabitMqConnector;
import main.entity.Tracking;
import main.entity.TrackingStep;
import main.service.TrackingService;
import main.util.ErrorUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/")
public class TrackingController {
    private TrackingService trackingService;

    public TrackingController(TrackingService trackingService, RabbitTemplate rabbitTemplate) {
        this.trackingService = trackingService;
    }

    @GetMapping("/tracking")
    public ResponseEntity<List<Tracking>> getTrackings() {
        return new ResponseEntity<>(this.trackingService.findAll(), HttpStatus.OK);
    }

    // TODO Voir pour renvoyer autre chose qu'une runtime
    @GetMapping("/tracking/{trackingId}")
    public ResponseEntity<Tracking> getTrackingById(@PathVariable(value = "trackingId") Long id) {
        Optional<Tracking> trackingOpt = this.trackingService.findById(id);
        if (trackingOpt.isPresent()) {
            return new ResponseEntity<>(trackingOpt.get(), HttpStatus.OK);
        } else {
            throw new RuntimeException(ErrorUtil.ENTITY_NOT_FOUND);
        }
    }

    @PostMapping("/tracking")
    public ResponseEntity<Tracking> postTrackings(@ModelAttribute Tracking tracking) {
        return new ResponseEntity<>(this.trackingService.save(tracking), HttpStatus.OK);
    }

    @PutMapping("/tracking")
    public ResponseEntity<Tracking> updateTracking(@ModelAttribute Tracking tracking) {
        return new ResponseEntity<>(this.trackingService.updateEntity(tracking), HttpStatus.OK);
    }

    // TODO Améliorer
    @DeleteMapping("/tracking/{trackingId}")
    public ResponseEntity<Boolean> deleteTrackingById(@PathVariable(value = "trackingId") Long id) {
        this.trackingService.deleteById(id);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PostMapping("/tracking/{trackingId}/step")
    public ResponseEntity<Tracking> addTrackingStep(@PathVariable(value="trackingId") Long id, @ModelAttribute TrackingStep trackingStep) {
        return new ResponseEntity<>(this.trackingService.addStep(id, trackingStep), HttpStatus.OK);
    }

    @DeleteMapping("/tracking/{trackingId}/step")
    public ResponseEntity<Boolean> removeTrackingStep(@PathVariable(value = "trackingId") Long idTracking, @RequestBody Long idStep) {
        return new ResponseEntity<>(this.trackingService.removeStep(idTracking, idStep), HttpStatus.OK);
    }
}
