package jv.triersistemas.atividades.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Tarefa {
	private Long id;
	private String titulo;
	private String descricao;
	private Boolean completa;
}
