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

    public static final String FILA_NOVO_CADASTRO_CURSO = "FILA_NOVO_CADASTRO_CURSO";

    @Bean // Substitui o conversor padrão, o SimpleMessageConverter, por um personalizado
    public Jackson2JsonMessageConverter converterObjetoParaEnviarPelaMensageria() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean // Agora usaremos o RabbitTemplate sem o SimpleMessageConverter, mas com o conversor personalizado (acima)
    public RabbitTemplate constroiRabbitTemplateComConversorPersonalizado(ConnectionFactory connectionFactory,
                                                                          Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Queue criarFila() {
        return QueueBuilder
                .durable(RabbitMQConfig.FILA_NOVO_CADASTRO_CURSO)
                .build();
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange("amq.fanout")
                .build();
    }

    @Bean
    public Binding ligacaoEntreFilaAndExchange(FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(criarFila())
                .to(fanoutExchange);
    }

    @Bean
    public RabbitAdmin criarRabbitAdminParaTarefasAdministrativas(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

//    @Bean
//    public ApplicationListener<ApplicationReadyEvent> inicializarAdminDoRabbitMQ(RabbitAdmin rabbitAdmin) {
//        return event -> rabbitAdmin.initialize();
//    }
}

