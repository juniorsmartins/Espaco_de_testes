package com.rabbitmq.produtor.connection;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;
import rabbitmq.ConstantesRabbitMQ;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {

    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange directExchange) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona() {
        Queue filaNum1 = this.fila(ConstantesRabbitMQ.FILA_NUM1);
        Queue filaNum2 = this.fila(ConstantesRabbitMQ.FILA_NUM2);

        DirectExchange trocaFila = this.trocaDireta();

        Binding ligacaoFilaNum1 = this.relacionamento(filaNum1, trocaFila);
        Binding ligacaoFilaNum2 = this.relacionamento(filaNum2, trocaFila);

        // Criar filas no RabbitMQ
        this.amqpAdmin.declareQueue(filaNum1);
        this.amqpAdmin.declareQueue(filaNum2);

        this.amqpAdmin.declareExchange(trocaFila);

        this.amqpAdmin.declareBinding(ligacaoFilaNum1);
        this.amqpAdmin.declareBinding(ligacaoFilaNum2);
    }
}
