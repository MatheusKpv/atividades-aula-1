package jv.triersistemas.atividades.model;

import jakarta.persistence.*;
import jv.triersistemas.atividades.dto.TarefaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity(name = "tarefa")
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String titulo;
	private String descricao;
	private Boolean completa;

	@Column(name = "data_criacao")
	private LocalDate dataCriacao;

	@Column(name = "data_expiracao")
	private LocalDate dataExpiracao;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	public Tarefa(TarefaDTO tarefaDTO, Categoria categoria) {
		titulo = tarefaDTO.titulo();
		descricao = tarefaDTO.descricao();
		completa = tarefaDTO.completa();
		this.categoria = categoria;
		this.dataCriacao = LocalDate.now();
		this.dataExpiracao = tarefaDTO.dataExpiracao();
	}
}
