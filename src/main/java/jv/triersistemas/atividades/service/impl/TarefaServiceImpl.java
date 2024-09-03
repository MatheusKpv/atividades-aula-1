package jv.triersistemas.atividades.service.impl;

import jv.triersistemas.atividades.dto.TarefaDTO;
import jv.triersistemas.atividades.dto.TarefaResponseDTO;
import jv.triersistemas.atividades.exception.BadRequestException;
import jv.triersistemas.atividades.exception.NotFoundException;
import jv.triersistemas.atividades.model.Categoria;
import jv.triersistemas.atividades.model.Tarefa;
import jv.triersistemas.atividades.repository.TarefaRepository;
import jv.triersistemas.atividades.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public TarefaResponseDTO getTarefa(Long id) {
        var tarefa = findById(id);
        return new TarefaResponseDTO(tarefa);
    }

    @Override
    public List<TarefaResponseDTO> getListaTarefas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return tarefas.stream().map(TarefaResponseDTO::new).toList();
    }

    @Override
    public List<TarefaResponseDTO> getListaTarefasPorTitulo(String titulo) {
        List<Tarefa> tarefas = tarefaRepository.findAllByTituloIgnoreCaseContaining(titulo);
        return tarefas.stream().map(TarefaResponseDTO::new).toList();
    }

    @Override
    public List<TarefaResponseDTO> getTarefasPorPeriodoDeExpiracao(Integer dias) {
        LocalDate dataPretendida = LocalDate.now().plusDays(dias);
        List<Tarefa> tarefas = tarefaRepository.findAllByDataExpiracaoBetween(LocalDate.now(), dataPretendida);
        return tarefas.stream().map(TarefaResponseDTO::new).toList();
    }

    @Override
    public List<Tarefa> getListaTarefaIncompletaPorCategoria(Categoria categoria) {
        return tarefaRepository.findAllByCategoriaAndCompletaIsFalse(categoria);
    }

    @Override
    public Integer countByCategoriaAndCompleta(Categoria categoria, Boolean completa) {
        return tarefaRepository.countByCategoriaAndCompleta(categoria, completa);
    }

    @Override
    public void deleteTarefa(Long id) {
        var tarefa = findById(id);
        tarefaRepository.delete(tarefa);
    }

    @Override
    public void validaCadastroTarefa(TarefaDTO tarefaDTO) {
        validaInformacoesMinimas(tarefaDTO);
        validaDataExpiracao(tarefaDTO.dataExpiracao());
    }

    private void validaInformacoesMinimas(TarefaDTO tarefaDTO) {
        if (tarefaDTO.titulo() == null && tarefaDTO.descricao() == null) {
            throw new BadRequestException("Informações insuficiente, favor preencher mais algum campo");
        }
    }

	@Override
    public void validaDataExpiracao(LocalDate dataExpiracao) {
        if (!dataExpiracao.isAfter(LocalDate.now())) {
            throw new BadRequestException("Data de expiração deve ser maior que hoje");
        }
    }

    @Override
    public void save(Tarefa tarefa) {
        tarefaRepository.save(tarefa);
    }

    @Override
    public Tarefa findById(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new NotFoundException("Id da tarefa não encontrado"));
    }

}
