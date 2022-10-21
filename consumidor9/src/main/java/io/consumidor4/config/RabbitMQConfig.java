package io.consumidor4.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_MENSAGENS_COMPLEXAS = "FILA_MENSAGENS_COMPLEXAS";
    public static final String EXCHANGE_FANOUT_MENSAGENS_COMPLEXAS = "EXCHANGE_FANOUT_MENSAGENS_COMPLEXAS";

    @Bean // Substitui o conversor padrão, o SimpleMessageConverter, por um personalizado
    public Jackson2JsonMessageConverter conversorJackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean // Agora usaremos o RabbitTemplate sem o SimpleMessageConverter, mas com o conversor personalizado (acima)
    public RabbitTemplate construirRabbitTemplateComConversorJackson2Json(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter conversorJackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(conversorJackson2JsonMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Queue fila() {
        return QueueBuilder
                .nonDurable(FILA_MENSAGENS_COMPLEXAS)
                .build();
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange(EXCHANGE_FANOUT_MENSAGENS_COMPLEXAS)
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

