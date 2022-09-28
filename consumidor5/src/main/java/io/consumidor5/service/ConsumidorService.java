package io.consumidor5.service;

import io.consumidor5.entities.Mensagem;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorService {

    public static final String FILA_CREATE_MENSAGEM2 = "FILA_CREATE_MENSAGEM2";

    @RabbitListener(queues = FILA_CREATE_MENSAGEM2)
    public void receberString(Mensagem mensagem) {
        System.out.println(mensagem.getAssunto() + "\n" + mensagem.getDescricao());
    }
}
