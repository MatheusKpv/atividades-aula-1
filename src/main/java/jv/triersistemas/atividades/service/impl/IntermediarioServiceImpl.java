package jv.triersistemas.atividades.service.impl;

import jv.triersistemas.atividades.dto.TarefaDTO;
import jv.triersistemas.atividades.dto.TarefaResponseDTO;
import jv.triersistemas.atividades.model.Categoria;
import jv.triersistemas.atividades.model.Tarefa;
import jv.triersistemas.atividades.service.CategoriaService;
import jv.triersistemas.atividades.service.IntermediarioService;
import jv.triersistemas.atividades.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntermediarioServiceImpl implements IntermediarioService {
    private final CategoriaService categoriaService;
    private final TarefaService tarefaService;

    @Autowired
    public IntermediarioServiceImpl(CategoriaService categoriaService, TarefaService tarefaService) {
        this.categoriaService = categoriaService;
        this.tarefaService = tarefaService;
    }

    @Override
    public List<TarefaResponseDTO> getTarefasIncompletasPorCategoria(Long id) {
        var categoria = categoriaService.findById(id);
        var tarefas = tarefaService.getListaTarefaIncompletaPorCategoria(categoria);
        return tarefas.stream().map(TarefaResponseDTO::new).toList();
    }

    @Override
    public Integer getCountTarefasPorCategoriaEStatus(Long idCategoria, Boolean completo) {
        Categoria categoria = categoriaService.findById(idCategoria);
        return tarefaService.countByCategoriaAndCompleta(categoria, completo);
    }

    @Override
    public TarefaResponseDTO cadastraTarefa(TarefaDTO tarefaDTO) {
        tarefaService.validaCadastroTarefa(tarefaDTO);
		var categoria = categoriaService.findById(tarefaDTO.categoria());
		Tarefa tarefa = new Tarefa(tarefaDTO, categoria);
		tarefaService.save(tarefa);
		return new TarefaResponseDTO(tarefa);
    }

    @Override
    public TarefaResponseDTO putTarefa(Long id, TarefaDTO tarefaDTO) {
        var tarefa = tarefaService.findById(id);
        if (tarefaDTO.titulo() != null) {
            tarefa.setTitulo(tarefaDTO.titulo());
        }
        if (tarefaDTO.descricao() != null) {
            tarefa.setDescricao(tarefaDTO.descricao());
        }
        if (tarefaDTO.completa() != null) {
            tarefa.setCompleta(tarefaDTO.completa());
        }
        if (tarefaDTO.dataExpiracao() != null) {
            tarefaService.validaDataExpiracao(tarefaDTO.dataExpiracao());
            tarefa.setDataExpiracao(tarefaDTO.dataExpiracao());
        }
		if (tarefaDTO.categoria() != null) {
			Categoria categoria = categoriaService.findById(tarefaDTO.categoria());
			tarefa.setCategoria(categoria);
		}
        tarefaService.save(tarefa);
        return new TarefaResponseDTO(tarefa);
    }
}
