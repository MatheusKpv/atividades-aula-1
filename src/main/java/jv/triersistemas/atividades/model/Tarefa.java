package jv.triersistemas.atividades.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jv.triersistemas.atividades.dto.TarefaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	public Tarefa(TarefaDTO tarefaDTO) {
		titulo = tarefaDTO.titulo();
		descricao = tarefaDTO.descricao();
		completa = tarefaDTO.completa();
	}
}
