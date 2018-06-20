package main.core.rabbit;

import main.util.StringUtil;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RabitMqConnector {

    private Environment environment;

    RabitMqConnector(Environment environment) {
        this.environment = environment;
    }


    Queue queue() {
        return new Queue(this.environment.getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_QUEUE_NAME), false);
    }


    TopicExchange exchange() {
        return new TopicExchange(this.environment.getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_EXCHANGE));
    }


    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(this.environment.getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_QUEUE_NAME));
    }


    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(this.environment.getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_QUEUE_NAME));
        container.setMessageListener(listenerAdapter);
        return container;
    }


    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
