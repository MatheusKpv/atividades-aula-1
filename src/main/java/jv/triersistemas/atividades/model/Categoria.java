package jv.triersistemas.atividades.model;

import jakarta.persistence.*;
import jv.triersistemas.atividades.dto.CategoriaDTO;
import jv.triersistemas.atividades.enuns.PrioridadeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String descricao;

    @Enumerated(EnumType.ORDINAL)
    private PrioridadeEnum prioridadeEnum;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<Tarefa> tarefas;

    public Categoria(CategoriaDTO dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.prioridadeEnum = dto.prioridadeEnum();
    }
}
