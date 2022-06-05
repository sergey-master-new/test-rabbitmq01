package com.example.testrabbitmq01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rabbitmq.host}")
    private String hostname;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.username}")
    private String userName;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    //    сonnectionFactory — для соединения с RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(hostname);
        connectionFactory.setPassword(password);
        connectionFactory.setUsername(userName);
        connectionFactory.setVirtualHost(virtualHost);

        return connectionFactory;
    }

    //    rabbitAdmin — для регистрации/отмены регистрации очередей и т.п.;
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    //    rabbitTemplate — для отправки сообщений (producer);
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    //объявляем очередь myQueue1 с именем queue1
    @Bean
    public Queue myQueue1() {
        return new Queue("queue2");
    }

//    Этот бин не нужен, так есть RabbitMqListener
//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer1() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setQueueNames("queue1");
//        container.setMessageListener(new MessageListener() {
//            //тут ловим сообщения из queue1
//            public void onMessage(Message message) {
//                log.info("log SimpleMessageListenerContainer - received from queue1 : " + new String(message.getBody()));
//            }
//        });
//        return container;
//    }

}
