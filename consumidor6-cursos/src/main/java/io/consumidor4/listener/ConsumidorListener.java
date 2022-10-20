package io.consumidor4.listener;

import io.consumidor4.config.RabbitMQConfig;
import io.consumidor4.dtos.CursoDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorListener {

    @RabbitListener(queues = RabbitMQConfig.FILA_NOVO_CADASTRO_CURSO)
    public void receberMensagem(@Payload CursoDTO cursoDTO) {
        String mensagem = """
                Título: %s
                Descrição: %s 
                """.formatted(cursoDTO.getTitulo(), cursoDTO.getDescricao());
        System.out.println(mensagem);
    }
}
