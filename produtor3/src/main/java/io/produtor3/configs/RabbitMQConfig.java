package io.produtor3.configs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.produtor3.uteis.RabbitMQConstantes;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Exchange criarCaixaDeCorreio() {
        return ExchangeBuilder
                .directExchange(RabbitMQConstantes.EXCHANGE_PROJETO)
                .durable(true)
                .build();
    }

    @Bean
    public Queue enderecoDeDestinoParaCreate() {
        return QueueBuilder
                .durable(RabbitMQConstantes.FILA_CREATE_PROJETO)
                .build();
    }

    @Bean
    public Queue enderecoDeDestinoParaUpdate() {
        return QueueBuilder
                .durable(RabbitMQConstantes.FILA_UPDATE_PROJETO)
                .build();
    }

    @Bean
    public Queue enderecoDeDestinoParaDelete() {
        return QueueBuilder
                .durable(RabbitMQConstantes.FILA_DELETE_PROJETO)
                .build();
    }

    @Bean
    public Binding criarRotaDeEntregaParaCreate() {
        return BindingBuilder.bind(enderecoDeDestinoParaCreate())
                .to(criarCaixaDeCorreio())
                .with(RabbitMQConstantes.ROTA_CREATE_PROJETO)
                .noargs();
    }

    @Bean
    public Binding criarRotaDeEntregaParaUpdate() {
        return BindingBuilder.bind(enderecoDeDestinoParaUpdate())
                .to(criarCaixaDeCorreio())
                .with(RabbitMQConstantes.ROTA_UPDATE_PROJETO)
                .noargs();
    }

    @Bean
    public Binding criarRotaDeEntregaParaDelete() {
        return BindingBuilder.bind(enderecoDeDestinoParaDelete())
                .to(criarCaixaDeCorreio())
                .with(RabbitMQConstantes.ROTA_DELETE_PROJETO)
                .noargs();
    }

//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        final ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        return new Jackson2JsonMessageConverter(objectMapper);
//    }
}
