package main.core.rabbit;

import com.google.gson.Gson;
import main.entity.Tracking;
import main.entity.TrackingStep;
import main.service.TrackingService;
import main.service.TrackingStepService;
import main.util.StringUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    private final RabbitTemplate rabbitTemplate;
    private Environment environment;
    private TrackingStepService trackingStepService;
    private TrackingService trackingService;

    public Listener(
            RabbitTemplate rabbitTemplate,
            Environment environment,
            TrackingStepService trackingStepService,
            TrackingService trackingService
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.environment = environment;
        this.trackingStepService = trackingStepService;
        this.trackingService = trackingService;
    }


    @RabbitListener(queues = "spring-micro-suivi-in")
    public void receiveMessageIn(final Message message) {
        System.out.println("Received message as generic: " +  new String (message.getBody()));

        Gson g = new Gson();

        Tracking trackingTmp = g.fromJson(new String (message.getBody()), Tracking.class);

        Tracking tracking = new Tracking();
        tracking.setName(trackingTmp.getName());
        tracking.setProductId(trackingTmp.getProductId());
        tracking.setUserId(trackingTmp.getUserId());

        TrackingStep trackingStep = new TrackingStep();
        trackingStep.setEtat(trackingTmp.retrieveLastStep().getEtat());
        trackingStep.setLieu(trackingTmp.retrieveLastStep().getLieu());
        trackingStep.setTracking(tracking);

        tracking.getTrackingSteps().add(trackingStep);

        trackingStepService.save(trackingStep);

        trackingService.save(tracking);

        System.out.println("A new tracking is created : " + tracking);

//        Tracking tracking = removeAndSaveTrackingStep(trackingStep, trackingOpt.get());

//        rabbitTemplate.convertAndSend(
//                this.environment.getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_EXCHANGE),
//                this.environment.getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_EMITER_QUEUE_NAME),
//                createPayload(tracking)
//        );
    }

    @RabbitListener(queues = "spring-micro-suivi-out")
    public void receiveMessageOut(final Message message) {
        System.out.println("Received message as generic: " +  new String (message.getBody()));
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

    private Tracking removeAndSaveTrackingStep(TrackingStep trackingStep, Tracking tracking) {
        trackingStep.setTracking(tracking);
        tracking.getTrackingSteps().add(this.trackingStepService.save(trackingStep));
//        tracking = this.save(tracking);
        return tracking;
    }
}
