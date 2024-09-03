package jv.triersistemas.atividades.service;

import jv.triersistemas.atividades.dto.TarefaDTO;
import jv.triersistemas.atividades.dto.TarefaResponseDTO;

import java.util.List;

public interface IntermediarioService {
    List<TarefaResponseDTO> getTarefasIncompletasPorCategoria(Long id);

    Integer getCountTarefasPorCategoriaEStatus(Long idCategoria, Boolean completo);

    TarefaResponseDTO cadastraTarefa(TarefaDTO tarefaDTO);

    TarefaResponseDTO putTarefa(Long id, TarefaDTO tarefaDTO);
}
