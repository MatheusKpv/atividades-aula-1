package jv.triersistemas.atividades.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import jv.triersistemas.atividades.model.Tarefa;
import jv.triersistemas.atividades.model.TarefaDTO;

public interface TarefaService {
	ResponseEntity<Tarefa> getTarefa(Long id);
	ResponseEntity<List<Tarefa>> getListaTarefas();
	ResponseEntity<String> cadastraTarefa(TarefaDTO tarefa);
	ResponseEntity<Tarefa> putTarefa(Long id, TarefaDTO tarefaDTO);
	ResponseEntity<String> deleteTarefa(Long id);
}
