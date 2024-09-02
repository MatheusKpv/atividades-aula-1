package jv.triersistemas.atividades.dto;

import jv.triersistemas.atividades.model.Categoria;

import java.time.LocalDate;

public record TarefaDTO(String titulo, String descricao, Boolean completa, LocalDate dataExpiracao, Long categoria) {
}
