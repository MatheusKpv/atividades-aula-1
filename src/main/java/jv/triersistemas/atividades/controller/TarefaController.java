package jv.triersistemas.atividades.controller;

import jv.triersistemas.atividades.model.Tarefa;
import jv.triersistemas.atividades.model.TarefaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tarefas")
public class TarefaController {
	private List<Tarefa> tarefas = new ArrayList<>();
	private Long cont = 0L;
	
	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> getTarefa(@PathVariable Long id) {
		Optional<Tarefa> opTarefa =  tarefas.stream().filter(t -> t.getId().equals(id)).findFirst();
        return opTarefa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//		OU
//		if (opTarefa.isPresent()) {
//			return ResponseEntity.ok(opTarefa.get());
//		}
//		return ResponseEntity.notFound().build();
    }

	@GetMapping
	public ResponseEntity<List<Tarefa>> getListaTarefas() {
		return ResponseEntity.ok(tarefas);
	}
	
	@PostMapping
	public ResponseEntity<String> cadastraTarefa(@RequestBody TarefaDTO tarefa) {
		if (tarefa.titulo() == null && tarefa.descricao() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Informações insuficiente, favor preencher mais algum campo");
		}
		cont++;
		Tarefa tarefaAtual = new Tarefa(cont, tarefa.titulo(), tarefa.descricao(), tarefa.completa());
		tarefas.add(tarefaAtual);
		return ResponseEntity.ok("Tarefa registrada com sucesso");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> putTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {
		Optional<Tarefa> opTarefa = tarefas.stream().filter(t -> t.getId().equals(id)).findFirst();
		if (opTarefa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Tarefa tarefaModificada = opTarefa.get();
		if (tarefaDTO.titulo() != null) {
			tarefaModificada.setTitulo(tarefaDTO.titulo());
		}
		if (tarefaDTO.descricao() != null) {
			tarefaModificada.setDescricao(tarefaDTO.descricao());
		}
		if (tarefaDTO.completa() != null) {
			tarefaModificada.setCompleta(tarefaDTO.completa());
		}
		return ResponseEntity.ok(tarefaModificada);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTarefa(@PathVariable Long id) {
		Optional<Tarefa> opTarefa = tarefas.stream().filter(t -> t.getId().equals(id)).findFirst();
		if (opTarefa.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID "+ id +" não encontrado");
		}
		tarefas.remove(opTarefa.get());
		return ResponseEntity.noContent().build();
	}
}
