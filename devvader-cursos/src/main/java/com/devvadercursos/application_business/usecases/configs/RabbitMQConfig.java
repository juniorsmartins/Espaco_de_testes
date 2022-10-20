package com.devvadercursos.application_business.usecases.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue criarFilaParaConduzirMensagens() {
        return QueueBuilder
                .nonDurable(RabbitMQFilas.FILA_NOVO_CADASTRO_CURSO)
                .build();
    }

    @Bean
    public RabbitAdmin criarRabbitAdminParaTarefasAdministrativas(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializarAdminDoRabbitMQ(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}

