package io.consumidor4;

import io.consumidor4.entities.Mensagem;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorListener {

    public static final String FILA_CREATE_MENSAGEM = "FILA_CREATE_MENSAGEM";

    @RabbitListener(queues = FILA_CREATE_MENSAGEM)
    public void receberString(String assunto) {
        System.out.println(assunto);
    }
}
