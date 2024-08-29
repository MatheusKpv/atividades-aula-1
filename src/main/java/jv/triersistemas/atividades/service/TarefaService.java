package jv.triersistemas.atividades.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import jv.triersistemas.atividades.model.Tarefa;
import jv.triersistemas.atividades.dto.TarefaDTO;

public interface TarefaService {
	Tarefa getTarefa(Long id);
	List<Tarefa> getListaTarefas();
	Tarefa cadastraTarefa(TarefaDTO tarefa);
	Tarefa putTarefa(Long id, TarefaDTO tarefaDTO);
	void deleteTarefa(Long id);
}
