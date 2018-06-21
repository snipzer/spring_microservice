package main.service;

import main.dao.ITrackingDao;
import main.entity.Tracking;
import main.entity.TrackingStep;
import main.util.StringUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrackingService extends BaseService<ITrackingDao, Tracking> {


    private final RabbitTemplate rabbitTemplate;

    private final TrackingStepService trackingStepService;

    private Environment environment;

    TrackingService(ITrackingDao iTrackingDao, RabbitTemplate rabbitTemplate, TrackingStepService trackingStepService, Environment environment) {
        super(iTrackingDao);
        this.rabbitTemplate = rabbitTemplate;
        this.trackingStepService = trackingStepService;
        this.environment = environment;
    }

    public Tracking addStep(Long idTracking, TrackingStep trackingStep) {
        Optional<Tracking> trackingOpt = this.findById(idTracking);
        if (trackingOpt.isPresent()) {
            Tracking tracking = removeAndSaveTrackingStep(trackingStep, trackingOpt.get());
            rabbitTemplate.convertAndSend(
                    this.environment.getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_EXCHANGE),
                    this.environment.getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_EMITER_QUEUE_NAME),
                    createPayload(tracking)
            );
            return tracking;
        } else {
            return null;
        }
    }

    private Tracking removeAndSaveTrackingStep(TrackingStep trackingStep, Tracking tracking) {
        trackingStep.setTracking(tracking);
        tracking.getTrackingSteps().add(this.trackingStepService.save(trackingStep));
        tracking = this.save(tracking);
        return tracking;
    }

    private String createPayload(Tracking tracking) {
        StringBuilder payloadBuilder = new StringBuilder();
        payloadBuilder.append("{\"user\": {\"id\": \"");
        payloadBuilder.append(tracking.getUserId());
        payloadBuilder.append("\"}, \"tracking\": { ");
        payloadBuilder.append("\"location\": \"").append(tracking.retrieveLastStep().getLieu()).append("\",");
        payloadBuilder.append("\"productId\": \"").append(tracking.getProductId()).append("\",");
        payloadBuilder.append("\"commandName\": \"").append(tracking.getName()).append("\"");
        payloadBuilder.append("}}");
        return payloadBuilder.toString();
    }

    public Tracking removeStep(Long idTracking, Long idStep) {
        Optional<Tracking> trackingOpt = this.findById(idTracking);
        if (trackingOpt.isPresent()) {
            Tracking tracking = trackingOpt.get();
            TrackingStep trackingStep = tracking.retrieveStepById(idStep);
            tracking.getTrackingSteps().remove(trackingStep);
            this.trackingStepService.deleteById(idStep);
            this.save(tracking);
            return tracking;
        } else {
            return null;
        }
    }
}
