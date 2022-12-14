package io.consumidor4.listener;

import io.consumidor4.config.RabbitMQConfig;
import io.consumidor4.entities.Mensagem;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorListener {

    @RabbitListener(queues = RabbitMQConfig.FILA_MENSAGENS_COMPLEXAS)
    public void receberMensagemComplexa(@Payload Mensagem mensagem) {
        String resposta = """
                Objeto Complexo
                Título: %s
                Descrição: %s
                """.formatted(mensagem.getAssunto(), mensagem.getDescricao());
        System.out.println(resposta);
    }
}
