package main.service;

import main.core.rabbit.RabitMqConnector;
import main.dao.ITrackingDao;
import main.entity.Tracking;
import main.entity.TrackingStep;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrackingService extends BaseService<ITrackingDao, Tracking> {

    private final RabbitTemplate rabbitTemplate;

    TrackingService(ITrackingDao iTrackingDao, RabbitTemplate rabbitTemplate) {
        super(iTrackingDao);
        this.rabbitTemplate = rabbitTemplate;
    }

    public Tracking addStep(Long idTracking, TrackingStep trackingStep) {
        Optional<Tracking> trackingOpt = this.findById(idTracking);
        if(trackingOpt.isPresent()) {
            Tracking tracking = trackingOpt.get();
            tracking.getTrackingSteps().add(trackingStep);
            tracking = this.save(tracking);
            rabbitTemplate.convertAndSend(RabitMqConnector.topicExchangeName, "foo.bar.baz", createPayload(tracking));
            return tracking;
        } else {
            return null;
        }
    }

    private String createPayload(Tracking tracking) {
        StringBuilder payloadBuilder = new StringBuilder();
        payloadBuilder.append("{\"user\": {\"id\": \"");
        payloadBuilder.append(tracking.getUserId());
        payloadBuilder.append("\"}, \"tracking\": { ");
        payloadBuilder.append("\"location\": \"").append(tracking.getLastStep().getLieu()).append("\",");
        payloadBuilder.append("\"productId\": \"").append(tracking.getProductId()).append("\",");
        payloadBuilder.append("\"commandName\": \"").append(tracking.getName()).append("\",");
        payloadBuilder.append("}}");
        return payloadBuilder.toString();
    }

    public Boolean removeStep(Long idTracking, Long idStep) {
        Optional<Tracking> trackingOpt = this.findById(idTracking);
        if(trackingOpt.isPresent()) {
            Tracking tracking = trackingOpt.get();
            return tracking.removeStepById(idStep);
        } else {
            return null;
        }
    }
}
