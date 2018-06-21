package main.core.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {



    @RabbitListener(queues = "spring-micro-suivi-in")
    public void receiveMessageIn(final Message message) {
        System.out.println("Received message as generic: " +  new String (message.getBody()));
    }

    @RabbitListener(queues = "spring-micro-suivi-out")
    public void receiveMessageOut(final Message message) {
        System.out.println("Received message as generic: " +  new String (message.getBody()));
    }
}
