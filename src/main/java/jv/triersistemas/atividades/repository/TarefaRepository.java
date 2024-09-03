package jv.triersistemas.atividades.repository;

import jv.triersistemas.atividades.model.Categoria;
import jv.triersistemas.atividades.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findAllByCategoriaAndCompletaIsFalse(Categoria categoria);

    List<Tarefa> findAllByTituloIgnoreCaseContaining(String titulo);

    List<Tarefa> findAllByDataExpiracaoBetween(LocalDate now, LocalDate dataPretendida);

    Integer countByCategoriaAndCompleta(Categoria categoria, Boolean completa);
}
