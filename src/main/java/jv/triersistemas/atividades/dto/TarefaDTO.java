package jv.triersistemas.atividades.dto;

import jv.triersistemas.atividades.model.Categoria;

public record TarefaDTO(String titulo, String descricao, Boolean completa, Long categoria) {
}
