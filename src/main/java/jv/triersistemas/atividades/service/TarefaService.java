package jv.triersistemas.atividades.service;

import jv.triersistemas.atividades.dto.TarefaDTO;
import jv.triersistemas.atividades.dto.TarefaResponseDTO;

import java.util.List;

public interface TarefaService {
	TarefaResponseDTO getTarefa(Long id);
	List<TarefaResponseDTO> getListaTarefas();
	TarefaResponseDTO cadastraTarefa(TarefaDTO tarefa);
	TarefaResponseDTO putTarefa(Long id, TarefaDTO tarefaDTO);
	void deleteTarefa(Long id);
}
