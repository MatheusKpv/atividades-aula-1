package jv.triersistemas.atividades.service.impl;

import jv.triersistemas.atividades.dto.TarefaDTO;
import jv.triersistemas.atividades.dto.TarefaResponseDTO;
import jv.triersistemas.atividades.exception.CategoriaNotFoundException;
import jv.triersistemas.atividades.exception.TarefaBadRequestException;
import jv.triersistemas.atividades.exception.TarefaNotFoundException;
import jv.triersistemas.atividades.model.Categoria;
import jv.triersistemas.atividades.model.Tarefa;
import jv.triersistemas.atividades.repository.CategoriaRepository;
import jv.triersistemas.atividades.repository.TarefaRepository;
import jv.triersistemas.atividades.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public TarefaResponseDTO getTarefa(Long id) {
		var tarefa = tarefaRepository.findById(id).orElseThrow(() -> new TarefaNotFoundException("Id não encontrado"));
		return new TarefaResponseDTO(tarefa);
	}

	@Override
	public List<TarefaResponseDTO> getListaTarefas() {
		List<Tarefa> tarefas = tarefaRepository.findAll();
		return tarefas.stream().map(TarefaResponseDTO::new).toList();
	}

	@Override
	public TarefaResponseDTO cadastraTarefa(TarefaDTO tarefaDTO) {
		validaCadastroTarefa(tarefaDTO);
		var categoria = categoriaRepository.findById(tarefaDTO.categoria()).orElseThrow(() -> new CategoriaNotFoundException("Id não encontrado"));
		Tarefa tarefa = new Tarefa(tarefaDTO, categoria);
		tarefaRepository.save(tarefa);
		return new TarefaResponseDTO(tarefa);
	}

	@Override
	public TarefaResponseDTO putTarefa(Long id, TarefaDTO tarefaDTO) {
		var tarefa = tarefaRepository.findById(id).orElseThrow(() -> new TarefaNotFoundException("Id não encontrado"));
		if (tarefaDTO.titulo() != null) {
			tarefa.setTitulo(tarefaDTO.titulo());
		}
		if (tarefaDTO.descricao() != null) {
			tarefa.setDescricao(tarefaDTO.descricao());
		}
		if (tarefaDTO.completa() != null) {
			tarefa.setCompleta(tarefaDTO.completa());
		}
		if (tarefaDTO.categoria() != null) {
			Categoria categoria = categoriaRepository.findById(tarefaDTO.categoria()).orElseThrow(() -> new CategoriaNotFoundException("Id não encontrado"));
			tarefa.setCategoria(categoria);
		}
		tarefaRepository.save(tarefa);
		return new TarefaResponseDTO(tarefa);
	}

	@Override
	public void deleteTarefa(Long id) {
		var tarefa = tarefaRepository.findById(id).orElseThrow(() -> new TarefaNotFoundException("Id não encontrado"));
		tarefaRepository.delete(tarefa);
	}

	private void validaCadastroTarefa(TarefaDTO tarefaDTO) {
		if (tarefaDTO.titulo() == null && tarefaDTO.descricao() == null) {
			throw new TarefaBadRequestException("Informações insuficiente, favor preencher mais algum campo");
		}
		if (tarefaDTO.dataExpiracao().isBefore(LocalDate.now())) {
			throw new TarefaBadRequestException("Data de expiração deve ser maior que hoje");
		}
	}

}
