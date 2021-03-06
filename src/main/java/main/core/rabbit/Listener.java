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


    @RabbitListener(queues = StringUtil.SPRING_MICRO_SUIVI_IN)
    public void receiveMessageIn(final Message message) {
        System.out.println("QUEUE-IN payload :" +  new String (message.getBody()));

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
        System.out.println("A new tracking is created : " + tracking.getName());

        String payload = this.trackingService.createPayload(trackingTmp);

        System.out.println("Sending notification on exchange: " + this.environment.getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_EXCHANGE) + " in queue :" + StringUtil.SPRING_MICRO_SUIVI_OUT);
//        rabbitTemplate.convertAndSend(
//                this.environment.getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_EXCHANGE),
//                StringUtil.SPRING_MICRO_SUIVI_OUT,
//                payload
//        );
        System.out.println("Notification send with payload: " + payload);
    }
}
