package jv.triersistemas.atividades.service.impl;

import jv.triersistemas.atividades.dto.CategoriaDTO;
import jv.triersistemas.atividades.dto.CategoriaResponseDTO;
import jv.triersistemas.atividades.exception.CategoriaBadRequestException;
import jv.triersistemas.atividades.exception.CategoriaNotFoundException;
import jv.triersistemas.atividades.exception.TarefaNotFoundException;
import jv.triersistemas.atividades.model.Categoria;
import jv.triersistemas.atividades.repository.CategoriaRepository;
import jv.triersistemas.atividades.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public CategoriaResponseDTO getCategoria(Long id) {
        var categoria = categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNotFoundException("Id não encontrado"));
        return new CategoriaResponseDTO(categoria);
    }

    @Override
    public List<CategoriaResponseDTO> getListaCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(CategoriaResponseDTO::new).toList();
    }

    @Override
    public CategoriaResponseDTO cadastraCategoria(CategoriaDTO categoriaDTO) {
        validaCadastroCategoria(categoriaDTO);
        Categoria categoria = new Categoria(categoriaDTO);
        categoriaRepository.save(categoria);
        return new CategoriaResponseDTO(categoria);
    }

    @Override
    public CategoriaResponseDTO putCategoria(Long id, CategoriaDTO categoriaDTO) {
        var categoria = categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNotFoundException("Id não encontrado"));
        if (categoriaDTO.nome() != null) {
            categoria.setNome(categoriaDTO.nome());
        }
        if (categoriaDTO.descricao() != null) {
            categoria.setDescricao(categoriaDTO.descricao());
        }
        if (categoriaDTO.prioridadeEnum() != null) {
            categoria.setPrioridadeEnum(categoriaDTO.prioridadeEnum());
        }
        categoriaRepository.save(categoria);
        return new CategoriaResponseDTO(categoria);
    }

    @Override
    public void deleteCategoria(Long id) {
        var categoria = categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNotFoundException("Id não encontrado"));
        categoriaRepository.delete(categoria);
    }

    private void validaCadastroCategoria(CategoriaDTO categoriaDTO) {
        if (categoriaDTO.nome() == null && categoriaDTO.descricao() == null) {
            throw new CategoriaBadRequestException("Informações insuficiente, favor preencher mais algum campo");
        }
    }
}
