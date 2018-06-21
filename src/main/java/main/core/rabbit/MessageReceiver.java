package main.core.rabbit;

import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessageIn(String message) {
        System.out.println("Received_in <" + message + ">");
        latch.countDown();
    }

    public void receiveMessageOut(String message) {
        System.out.println("Received_out <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
