package main.core.rabbit;

import main.util.StringUtil;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RabbitMqConnectorOut extends BaseRabbitConnector {

    RabbitMqConnectorOut(Environment environment) {
        super(environment);
    }


    @Bean("queueOut")
    Queue queue() {
        return new Queue(getEnvironment().getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_EMITER_QUEUE_NAME), false);
    }

    @Autowired
    Binding binding(@Qualifier("queueOut") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(getEnvironment().getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_ROUTING_KEY_OUT));
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapterOut) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(getEnvironment().getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_EMITER_QUEUE_NAME));
        container.setMessageListener(listenerAdapterOut);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapterOut(MessageReceiver messageReceiver) {
        return new MessageListenerAdapter(messageReceiver, "receiveMessageOut");
    }
}