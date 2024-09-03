package jv.triersistemas.atividades.controller;

import jv.triersistemas.atividades.dto.CategoriaDTO;
import jv.triersistemas.atividades.dto.CategoriaResponseDTO;
import jv.triersistemas.atividades.dto.ErrorResponseDTO;
import jv.triersistemas.atividades.dto.TarefaResponseDTO;
import jv.triersistemas.atividades.exception.BadRequestException;
import jv.triersistemas.atividades.exception.NotFoundException;
import jv.triersistemas.atividades.service.CategoriaService;
import jv.triersistemas.atividades.service.IntermediarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final IntermediarioService intermediarioService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService, IntermediarioService intermediarioService) {
        this.categoriaService = categoriaService;
        this.intermediarioService = intermediarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoria(@PathVariable Long id) {
        try {
            CategoriaResponseDTO categoria = categoriaService.getCategoria(id);
            return ResponseEntity.ok(categoria);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getListaCategorias() {
        try {
            List<CategoriaResponseDTO> categorias = categoriaService.getListaCategorias();
            return ResponseEntity.ok(categorias);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @GetMapping("/tarefas-incompletas-por-categoria/{id}")
    public ResponseEntity<?> getTarefasIncompletasPorCategoria(@PathVariable Long id) {
        try {
            List<TarefaResponseDTO> tarefas = intermediarioService.getTarefasIncompletasPorCategoria(id);
            return ResponseEntity.ok(tarefas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastraCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        try {
            CategoriaResponseDTO categoria = categoriaService.cadastraCategoria(categoriaDTO);
            return ResponseEntity.ok(categoria);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        try {
            CategoriaResponseDTO categoria = categoriaService.putCategoria(id, categoriaDTO);
            return ResponseEntity.ok(categoria);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ErrorResponseDTO> deleteCategoria(@PathVariable Long id) {
        try {
            categoriaService.deleteCategoria(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }
}
