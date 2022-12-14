package io.consumidor4.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_MENSAGENS_COMPLEXAS = "FILA_MENSAGENS_COMPLEXAS";

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
}

