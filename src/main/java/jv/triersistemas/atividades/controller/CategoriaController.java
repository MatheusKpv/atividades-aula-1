package jv.triersistemas.atividades.controller;

import jv.triersistemas.atividades.dto.CategoriaDTO;
import jv.triersistemas.atividades.dto.CategoriaResponseDTO;
import jv.triersistemas.atividades.dto.ErrorResponseDTO;
import jv.triersistemas.atividades.exception.CategoriaBadRequestException;
import jv.triersistemas.atividades.exception.CategoriaNotFoundException;
import jv.triersistemas.atividades.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoria(@PathVariable Long id) {
        try {
            CategoriaResponseDTO categoria = categoriaService.getCategoria(id);
            return ResponseEntity.ok(categoria);
        } catch (CategoriaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> getListaCategorias() {
        List<CategoriaResponseDTO> categorias = categoriaService.getListaCategorias();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<?> cadastraCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        try {
            CategoriaResponseDTO categoria = categoriaService.cadastraCategoria(categoriaDTO);
            return ResponseEntity.ok(categoria);
        } catch (CategoriaBadRequestException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        try {
            CategoriaResponseDTO categoria = categoriaService.putCategoria(id, categoriaDTO);
            return ResponseEntity.ok(categoria);
        } catch (CategoriaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ErrorResponseDTO> deleteCategoria(@PathVariable Long id) {
        try {
            categoriaService.deleteCategoria(id);
            return ResponseEntity.noContent().build();
        } catch (CategoriaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
        }
    }
}
