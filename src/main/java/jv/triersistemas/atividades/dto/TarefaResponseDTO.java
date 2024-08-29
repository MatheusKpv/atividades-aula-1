package jv.triersistemas.atividades.dto;

import jv.triersistemas.atividades.model.Tarefa;

public record TarefaResponseDTO(Long id, String titulo, String descricao, Boolean completa, CategoriaResponseDTO categoria) {
    public TarefaResponseDTO(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getCompleta(), new CategoriaResponseDTO(tarefa.getCategoria()));
    }
}
