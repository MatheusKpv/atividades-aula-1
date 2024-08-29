package jv.triersistemas.atividades.service.impl;

import java.util.List;
import java.util.Optional;

import jv.triersistemas.atividades.exception.TarefaBadRequestException;
import jv.triersistemas.atividades.exception.TarefaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jv.triersistemas.atividades.model.Tarefa;
import jv.triersistemas.atividades.dto.TarefaDTO;
import jv.triersistemas.atividades.repository.TarefaRepository;
import jv.triersistemas.atividades.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Override
	public Tarefa getTarefa(Long id) {
		var tarefa = tarefaRepository.findById(id);
		validaTarefaVazia(tarefa);
		return tarefa.get();
	}

	@Override
	public List<Tarefa> getListaTarefas() {
		List<Tarefa> tarefas = tarefaRepository.findAll();
		return tarefas;
	}

	@Override
	public Tarefa cadastraTarefa(TarefaDTO tarefaDTO) {
		validaCadastroTarefa(tarefaDTO);
		Tarefa tarefa = new Tarefa(tarefaDTO);
		tarefaRepository.save(tarefa);
		return tarefa;
	}

	@Override
	public Tarefa putTarefa(Long id, TarefaDTO tarefaDTO) {
		var tarefa = tarefaRepository.findById(id);
		validaTarefaVazia(tarefa);
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
		return tarefaModificada;
	}

	@Override
	public void deleteTarefa(Long id) {
		var tarefa = tarefaRepository.findById(id);
		validaTarefaVazia(tarefa);
		tarefaRepository.delete(tarefa.get());
	}

	private void validaCadastroTarefa(TarefaDTO tarefaDTO) {
		if (tarefaDTO.titulo() == null && tarefaDTO.descricao() == null) {
			throw new TarefaBadRequestException("Informações insuficiente, favor preencher mais algum campo");
		}
	}

	private void validaTarefaVazia(Optional<Tarefa> tarefa) {
		if (tarefa.isEmpty()) {
			throw new TarefaNotFoundException("Id não encontrado");
		}
	}

}
