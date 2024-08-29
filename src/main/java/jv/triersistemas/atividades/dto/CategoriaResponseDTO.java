package jv.triersistemas.atividades.dto;

import jv.triersistemas.atividades.enuns.PrioridadeEnum;
import jv.triersistemas.atividades.model.Categoria;

public record CategoriaResponseDTO(Long id, String nome, String descricao, PrioridadeEnum prioridadeEnum) {
    public CategoriaResponseDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome(), categoria.getDescricao(), categoria.getPrioridadeEnum());
    }
}
