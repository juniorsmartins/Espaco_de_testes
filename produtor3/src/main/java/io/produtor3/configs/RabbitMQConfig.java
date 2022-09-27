package io.produtor3.configs;

import io.produtor3.uteis.RabbitMQConstantes;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitMQConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    public DirectExchange criarCaixaDeCorreio() {
        return ExchangeBuilder
                .directExchange(RabbitMQConstantes.NOME_EXCHANGE_PROJETO)
                .durable(true)
                .build();
    }


}
