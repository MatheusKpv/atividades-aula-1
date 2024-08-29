package jv.triersistemas.atividades.dto;

import jv.triersistemas.atividades.enuns.PrioridadeEnum;

public record CategoriaDTO(String nome, String descricao, PrioridadeEnum prioridadeEnum) {
}
