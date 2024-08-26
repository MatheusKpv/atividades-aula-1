package jv.triersistemas.atividades.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {
	private Long id;
	private String titulo;
	private String descricao;
	private Boolean completa;
}
