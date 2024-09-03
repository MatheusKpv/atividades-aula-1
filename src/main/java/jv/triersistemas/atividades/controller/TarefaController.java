package jv.triersistemas.atividades.controller;

import jv.triersistemas.atividades.dto.ErrorResponseDTO;
import jv.triersistemas.atividades.dto.TarefaDTO;
import jv.triersistemas.atividades.dto.TarefaResponseDTO;
import jv.triersistemas.atividades.exception.BadRequestException;
import jv.triersistemas.atividades.exception.NotFoundException;
import jv.triersistemas.atividades.service.IntermediarioService;
import jv.triersistemas.atividades.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tarefas")
public class TarefaController {

	private final TarefaService tarefaService;
	private final IntermediarioService intermediarioService;

	@Autowired
	public TarefaController(TarefaService tarefaService, IntermediarioService intermediarioService) {
		this.tarefaService = tarefaService;
		this.intermediarioService = intermediarioService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTarefa(@PathVariable Long id) {
		try {
			TarefaResponseDTO tarefa = tarefaService.getTarefa(id);
			return ResponseEntity.ok(tarefa);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
		}
    }

	@GetMapping
	public ResponseEntity<?> getListaTarefas() {
		try {
			List<TarefaResponseDTO> tarefas = tarefaService.getListaTarefas();
			return ResponseEntity.ok(tarefas);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
		}
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<?> getTarefasPorTitulo(@PathVariable String titulo) {
		try {
			List<TarefaResponseDTO> tarefas = tarefaService.getListaTarefasPorTitulo(titulo);
			return ResponseEntity.ok(tarefas);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
		}
	}

	@GetMapping("/periodo/{dias}")
	public ResponseEntity<?> getTarefasPorPeriodoDeExpiracao(@PathVariable Integer dias) {
		try {
			List<TarefaResponseDTO> tarefas = tarefaService.getTarefasPorPeriodoDeExpiracao(dias);
			return ResponseEntity.ok(tarefas);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
		}
	}

	@GetMapping("/count-categoria-status")
	public ResponseEntity<?> getCountTarefasPorCategoriaEStatus(@RequestParam Long idCategoria, @RequestParam Boolean completo) {
		try {
			Integer count = intermediarioService.getCountTarefasPorCategoriaEStatus(idCategoria, completo);
			return ResponseEntity.ok(count);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
		}
	}
	
	@PostMapping
	public ResponseEntity<?> cadastraTarefa(@RequestBody TarefaDTO tarefaDTO) {
		try {
			TarefaResponseDTO tarefa = intermediarioService.cadastraTarefa(tarefaDTO);
			return ResponseEntity.ok(tarefa);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> putTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {
		try {
			//TarefaResponseDTO tarefa = tarefaService.putTarefa(id, tarefaDTO);
			TarefaResponseDTO tarefa = intermediarioService.putTarefa(id, tarefaDTO);
			return ResponseEntity.ok(tarefa);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ErrorResponseDTO> deleteTarefa(@PathVariable Long id) {
		try {
			tarefaService.deleteTarefa(id);
			return ResponseEntity.noContent().build();
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
		}
	}
}
