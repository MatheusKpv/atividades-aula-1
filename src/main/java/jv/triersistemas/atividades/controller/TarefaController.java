package jv.triersistemas.atividades.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jv.triersistemas.atividades.model.Tarefa;
import jv.triersistemas.atividades.model.TarefaDTO;

@RestController
@RequestMapping("tarefas")
public class TarefaController {
	private List<Tarefa> tarefas = new ArrayList<>();
	private Long cont = 0L;
	
	@GetMapping("/{id}")
	public Tarefa getListaTarefas(@PathVariable Long id) {
		return tarefas.forEach(c -> c.ge)
	}
	
	@PostMapping
	public void cadastraTarefa(@RequestBody TarefaDTO tarefa) {
		cont++;
		Tarefa tarefaAtual = new Tarefa(cont, tarefa.titulo(), tarefa.descricao(), tarefa.completa());
		tarefas.add(tarefaAtual);
	}
}
