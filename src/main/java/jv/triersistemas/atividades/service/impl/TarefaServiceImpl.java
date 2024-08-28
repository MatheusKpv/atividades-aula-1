package jv.triersistemas.atividades.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jv.triersistemas.atividades.model.Tarefa;
import jv.triersistemas.atividades.model.TarefaDTO;
import jv.triersistemas.atividades.repository.TarefaRepository;
import jv.triersistemas.atividades.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Override
	public ResponseEntity<Tarefa> getTarefa(Long id) {
		var tarefa = tarefaRepository.findById(id);
		if (tarefa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tarefa.get());
	}

	@Override
	public ResponseEntity<List<Tarefa>> getListaTarefas() {
		List<Tarefa> tarefas = tarefaRepository.findAll();
		return ResponseEntity.ok(tarefas);
	}

	@Override
	public ResponseEntity<String> cadastraTarefa(TarefaDTO tarefaDTO) {
		if (tarefaDTO.titulo() == null && tarefaDTO.descricao() == null) {
			//throw new RuntimeException("Informações insuficiente, favor preencher mais algum campo");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Informações insuficiente, favor preencher mais algum campo");
		}
		Tarefa tarefaAtual = new Tarefa(tarefaDTO);
		tarefaRepository.save(tarefaAtual);
		return ResponseEntity.ok("Tarefa registrada com sucesso");
	}

	@Override
	public ResponseEntity<Tarefa> putTarefa(Long id, TarefaDTO tarefaDTO) {
		var tarefa = tarefaRepository.findById(id);
		if (tarefa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Tarefa tarefaModificada = tarefa.get();
		if (tarefaDTO.titulo() != null) {
			tarefaModificada.setTitulo(tarefaDTO.titulo());
		}
		if (tarefaDTO.descricao() != null) {
			tarefaModificada.setDescricao(tarefaDTO.descricao());
		}
		if (tarefaDTO.completa() != null) {
			tarefaModificada.setCompleta(tarefaDTO.completa());
		}
		tarefaRepository.save(tarefaModificada);
		return ResponseEntity.ok(tarefaModificada);
	}

	@Override
	public ResponseEntity<String> deleteTarefa(Long id) {
		var tarefa = tarefaRepository.findById(id);
		if (tarefa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		tarefaRepository.delete(tarefa.get());
		return ResponseEntity.noContent().build();
	}

}
