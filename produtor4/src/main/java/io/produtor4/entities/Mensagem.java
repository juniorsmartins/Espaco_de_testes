package io.produtor4.entities;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem {

    private String assunto;
    private String descricao;
}
