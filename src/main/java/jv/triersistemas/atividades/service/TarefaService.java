package jv.triersistemas.atividades.service;

import jv.triersistemas.atividades.dto.TarefaDTO;
import jv.triersistemas.atividades.dto.TarefaResponseDTO;
import jv.triersistemas.atividades.model.Categoria;
import jv.triersistemas.atividades.model.Tarefa;

import java.time.LocalDate;
import java.util.List;

public interface TarefaService {
	TarefaResponseDTO getTarefa(Long id);
	List<TarefaResponseDTO> getListaTarefas();
	List<TarefaResponseDTO> getListaTarefasPorTitulo(String titulo);
	List<TarefaResponseDTO> getTarefasPorPeriodoDeExpiracao(Integer dias);
	List<Tarefa> getListaTarefaIncompletaPorCategoria(Categoria categoria);
	Integer countByCategoriaAndCompleta(Categoria categoria, Boolean completa);
	void deleteTarefa(Long id);
	void validaCadastroTarefa(TarefaDTO tarefaDTO);

	void validaDataExpiracao(LocalDate dataExpiracao);

	void save(Tarefa tarefa);
	Tarefa findById(Long id);
}
