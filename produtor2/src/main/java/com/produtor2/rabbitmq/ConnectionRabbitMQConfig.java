package com.produtor2.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConnectionRabbitMQConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    public static final String NOME_EXCHANGE = "amq.direct";

    public static final String NOME_FILA_CREATE = "FILA_CREATE";
    public static final String NOME_FILA_UPDATE = "FILA_UPDATE";
    public static final String NOME_FILA_DELETE = "FILA_DELETE";

    public static final String NOME_ROTA_CREATE = "ROTA_CREATE";
    public static final String NOME_ROTA_UPDATE = "ROTA_UPDATE";
    public static final String NOME_ROTA_DELETE = "ROTA_DELETE";

    public DirectExchange declararExchange() {
        return ExchangeBuilder
                .directExchange(NOME_EXCHANGE)
                .durable(true)
                .build();
    }

    private Queue declararFila(String nomeFila) {
        return QueueBuilder
                .durable(nomeFila)
                .build();
    }

    private Binding declararRelacionamentoEntreExchangeAndFila(Exchange exchange, Queue fila, String rota) {
        return BindingBuilder.bind(fila)
                .to(exchange)
                .with(rota)
                .noargs();
    }

    @PostConstruct
    private void construirConnectionRabbitMQ() {
        DirectExchange directExchange = this.declararExchange();

        Queue filaCreate = this.declararFila(NOME_FILA_CREATE);
        Queue filaUpdate = this.declararFila(NOME_FILA_UPDATE);
        Queue filaDelete = this.declararFila(NOME_FILA_DELETE);

        Binding rotaCreate = this.declararRelacionamentoEntreExchangeAndFila(directExchange, filaCreate, NOME_ROTA_CREATE);
        Binding rotaUpdate = this.declararRelacionamentoEntreExchangeAndFila(directExchange, filaUpdate, NOME_ROTA_UPDATE);
        Binding rotaDelete = this.declararRelacionamentoEntreExchangeAndFila(directExchange, filaDelete, NOME_ROTA_DELETE);

        this.amqpAdmin.declareExchange(directExchange);
        this.amqpAdmin.declareQueue(filaCreate);
        this.amqpAdmin.declareQueue(filaUpdate);
        this.amqpAdmin.declareQueue(filaDelete);
        this.amqpAdmin.declareBinding(rotaCreate);
        this.amqpAdmin.declareBinding(rotaUpdate);
        this.amqpAdmin.declareBinding(rotaDelete);
    }
}
