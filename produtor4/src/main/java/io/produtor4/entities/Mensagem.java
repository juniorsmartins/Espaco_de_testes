package io.produtor4.entities;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Mensagem implements Serializable{

    private String assunto;
    private String descricao;
}
