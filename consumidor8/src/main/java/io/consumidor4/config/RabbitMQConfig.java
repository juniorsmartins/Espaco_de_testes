package io.consumidor4.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_MENSAGENS_SIMPLES = "FILA_MENSAGENS_SIMPLES";
    public static final String EXCHANGE_FANOUT_MENSAGENS_SIMPLES = "EXCHANGE_FANOUT_MENSAGENS_SIMPLES";

    @Bean
    public Queue fila() {
        return QueueBuilder
                .nonDurable(FILA_MENSAGENS_SIMPLES)
                .build();
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange(EXCHANGE_FANOUT_MENSAGENS_SIMPLES)
                .build();
    }

    @Bean
    public Binding bindingFilaFanoutExchange(Queue fila, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(fila)
                .to(fanoutExchange);
    }

    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializarAdminParaTarefas(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}

