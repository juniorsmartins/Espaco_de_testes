package io.produtor5.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
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

    public static final String FILA_MENSAGENS_SIMPLES = "FILA_MENSAGENS_SIMPLES";
    public static final String FILA_MENSAGENS_COMPLEXAS = "FILA_MENSAGENS_COMPLEXAS";
    public static final String EXCHANGE_MENSAGEM_FANOUT = "mensagem.fanout";

    @Bean
    public Queue criarFilaParaMensagensSimples() {
        return QueueBuilder
                .nonDurable(RabbitMQConfig.FILA_MENSAGENS_SIMPLES)
                .build();
    }

    @Bean
    public Queue criarFilaParaMensagensComplexas() {
        return QueueBuilder
                .nonDurable(RabbitMQConfig.FILA_MENSAGENS_COMPLEXAS)
                .build();
    }

//    @Bean
//    public FanoutExchange criarFanoutExchange() {
//        return new FanoutExchange(RabbitMQConfig.EXCHANGE_MENSAGEM_FANOUT);
//    }

    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializarAdminParaTarefas(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

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
}
