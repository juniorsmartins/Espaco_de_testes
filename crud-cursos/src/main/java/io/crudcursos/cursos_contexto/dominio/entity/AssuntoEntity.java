package io.crudcursos.cursos_contexto.dominio.entity;

import io.crudcursos.cursos_contexto.dominio.dto.AssuntoDTORequest;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "assuntos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public final class AssuntoEntity implements Serializable, IEntity<Long> {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tema")
    private String tema;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "assunto", targetEntity = CursoEntity.class)
    private List<CursoEntity> cursos;

    public AssuntoEntity(AssuntoDTORequest assuntoDTORequest) {
        this.id = assuntoDTORequest.id();
        this.tema = assuntoDTORequest.tema();
    }
}
