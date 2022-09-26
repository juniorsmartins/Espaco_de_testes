package com.consumidor2.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {

    @RabbitListener
    private void consumidor() {

    }
}
