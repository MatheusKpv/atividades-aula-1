package jv.triersistemas.atividades.controller;

import java.util.List;

import jv.triersistemas.atividades.dto.ErrorResponseDTO;
import jv.triersistemas.atividades.exception.TarefaBadRequestException;
import jv.triersistemas.atividades.exception.TarefaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import jv.triersistemas.atividades.dto.TarefaDTO;
import jv.triersistemas.atividades.service.TarefaService;

@RestController
@RequestMapping("tarefas")
public class TarefaController {
	
	@Autowired
	private TarefaService tarefaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTarefa(@PathVariable Long id) {
		try {
			Tarefa tarefa = tarefaService.getTarefa(id);
			return ResponseEntity.ok(tarefa);
		} catch (TarefaNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
		}
    }

	@GetMapping
	public ResponseEntity<List<Tarefa>> getListaTarefas() {
		List<Tarefa> tarefas = tarefaService.getListaTarefas();
		return ResponseEntity.ok(tarefas);
	}
	
	@PostMapping
	public ResponseEntity<?> cadastraTarefa(@RequestBody TarefaDTO tarefaDTO) {
		try {
			Tarefa tarefa = tarefaService.cadastraTarefa(tarefaDTO);
			return ResponseEntity.ok(tarefa);
		} catch (TarefaBadRequestException e) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> putTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {
		try {
			Tarefa tarefa = tarefaService.putTarefa(id, tarefaDTO);
			return ResponseEntity.ok(tarefa);
		} catch (TarefaNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ErrorResponseDTO> deleteTarefa(@PathVariable Long id) {
		try {
			tarefaService.deleteTarefa(id);
			return ResponseEntity.noContent().build();
		} catch (TarefaNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
		}
	}
}
