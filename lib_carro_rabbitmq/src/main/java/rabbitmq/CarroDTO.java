package rabbitmq;

import lombok.*;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class CarroDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String marca;
    private String cor;
    private int ano;
}
