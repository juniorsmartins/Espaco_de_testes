package io.consumidor3.services;

import io.consumidor3.dtos.ProjetoDTO;
import io.consumidor3.uteis.RabbitMQConstantes;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public final class ConsumidorServices {

    @RabbitListener(queues = RabbitMQConstantes.FILA_CREATE_PROJETO)
    private void consumidor(ProjetoDTO projetoDTO) {
        System.out.println(projetoDTO);
    }
}
