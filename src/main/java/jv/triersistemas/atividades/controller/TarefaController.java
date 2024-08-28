package jv.triersistemas.atividades.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jv.triersistemas.atividades.model.Tarefa;
import jv.triersistemas.atividades.model.TarefaDTO;
import jv.triersistemas.atividades.service.TarefaService;

@RestController
@RequestMapping("tarefas")
public class TarefaController {
	
	@Autowired
	private TarefaService tarefaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> getTarefa(@PathVariable Long id) {
		return tarefaService.getTarefa(id);
    }

	@GetMapping
	public ResponseEntity<List<Tarefa>> getListaTarefas() {
		return tarefaService.getListaTarefas();
	}
	
	@PostMapping
	public ResponseEntity<String> cadastraTarefa(@RequestBody TarefaDTO tarefa) {
		return tarefaService.cadastraTarefa(tarefa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> putTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {
		return tarefaService.putTarefa(id, tarefaDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTarefa(@PathVariable Long id) {
		return tarefaService.deleteTarefa(id);
	}
}
