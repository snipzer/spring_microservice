package main.core.rabbit;

import main.util.StringUtil;
import org.springframework.core.env.Environment;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class BaseRabbitConnector {

    private Environment environment;

    BaseRabbitConnector(Environment environment) {
        this.environment = environment;
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(this.environment.getProperty(StringUtil.SPRING_RABBITMQ_TEMPLATE_EXCHANGE));
    }

    public Environment getEnvironment() {
        return this.environment;
    }
}
