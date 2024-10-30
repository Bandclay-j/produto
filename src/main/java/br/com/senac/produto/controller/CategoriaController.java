package br.com.senac.produto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.produto.repository.CategoriaRepository;

@RestController
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/categoria")
    public ResponseEntity<?> getCategorias() {        
        return new ResponseEntity<>(categoriaRepository.findAll(), HttpStatus.OK);
    }
}