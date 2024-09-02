package jv.triersistemas.atividades.dto;

import jv.triersistemas.atividades.model.Tarefa;

import java.time.LocalDate;

public record TarefaResponseDTO(Long id, String titulo, String descricao, Boolean completa, LocalDate dataCriacao,
                                LocalDate dataExpiracao, CategoriaResponseDTO categoria) {
    public TarefaResponseDTO(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getCompleta(), tarefa.getDataCriacao(),
                tarefa.getDataExpiracao(), new CategoriaResponseDTO(tarefa.getCategoria()));
    }
}
