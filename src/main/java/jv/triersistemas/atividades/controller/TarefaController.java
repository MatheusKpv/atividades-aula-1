package jv.triersistemas.atividades.controller;

import jv.triersistemas.atividades.dto.ErrorResponseDTO;
import jv.triersistemas.atividades.dto.TarefaDTO;
import jv.triersistemas.atividades.dto.TarefaResponseDTO;
import jv.triersistemas.atividades.exception.CategoriaNotFoundException;
import jv.triersistemas.atividades.exception.TarefaBadRequestException;
import jv.triersistemas.atividades.exception.TarefaNotFoundException;
import jv.triersistemas.atividades.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tarefas")
public class TarefaController {
	
	@Autowired
	private TarefaService tarefaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTarefa(@PathVariable Long id) {
		try {
			TarefaResponseDTO tarefa = tarefaService.getTarefa(id);
			return ResponseEntity.ok(tarefa);
		} catch (TarefaNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
		}
    }

	@GetMapping
	public ResponseEntity<List<TarefaResponseDTO>> getListaTarefas() {
		List<TarefaResponseDTO> tarefas = tarefaService.getListaTarefas();
		return ResponseEntity.ok(tarefas);
	}
	
	@PostMapping
	public ResponseEntity<?> cadastraTarefa(@RequestBody TarefaDTO tarefaDTO) {
		try {
			TarefaResponseDTO tarefa = tarefaService.cadastraTarefa(tarefaDTO);
			return ResponseEntity.ok(tarefa);
		} catch (TarefaBadRequestException e) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
		} catch (TarefaNotFoundException | CategoriaNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> putTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {
		try {
			TarefaResponseDTO tarefa = tarefaService.putTarefa(id, tarefaDTO);
			return ResponseEntity.ok(tarefa);
		} catch (TarefaNotFoundException | CategoriaNotFoundException e) {
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
