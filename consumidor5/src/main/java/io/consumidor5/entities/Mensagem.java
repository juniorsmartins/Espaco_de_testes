package io.consumidor5.entities;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Mensagem {

    private String assunto;
    private String descricao;
}
