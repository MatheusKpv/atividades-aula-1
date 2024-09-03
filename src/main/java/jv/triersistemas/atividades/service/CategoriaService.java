package jv.triersistemas.atividades.service;

import jv.triersistemas.atividades.dto.CategoriaDTO;
import jv.triersistemas.atividades.dto.CategoriaResponseDTO;
import jv.triersistemas.atividades.dto.TarefaResponseDTO;
import jv.triersistemas.atividades.model.Categoria;

import java.util.List;

public interface CategoriaService {
    CategoriaResponseDTO getCategoria(Long id);
    List<CategoriaResponseDTO> getListaCategorias();
    CategoriaResponseDTO cadastraCategoria(CategoriaDTO categoria);
    CategoriaResponseDTO putCategoria(Long id, CategoriaDTO categoriaDTO);
    void deleteCategoria(Long id);
    Categoria findById(Long id);
}
