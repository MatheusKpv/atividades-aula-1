package jv.triersistemas.atividades.service.impl;

import jv.triersistemas.atividades.dto.CategoriaDTO;
import jv.triersistemas.atividades.dto.CategoriaResponseDTO;
import jv.triersistemas.atividades.exception.BadRequestException;
import jv.triersistemas.atividades.exception.NotFoundException;
import jv.triersistemas.atividades.model.Categoria;
import jv.triersistemas.atividades.repository.CategoriaRepository;
import jv.triersistemas.atividades.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public CategoriaResponseDTO getCategoria(Long id) {
        var categoria = findById(id);
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
        var categoria = findById(id);
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
        var categoria = findById(id);
        categoriaRepository.delete(categoria);
    }

    private void validaCadastroCategoria(CategoriaDTO categoriaDTO) {
        if (categoriaDTO.nome() == null && categoriaDTO.descricao() == null) {
            throw new BadRequestException("Informações insuficiente, favor preencher mais algum campo");
        }
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new NotFoundException("Id da categoria não encontrado"));
    }
}
