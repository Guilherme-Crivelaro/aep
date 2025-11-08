package com.aep.doacaobooks.livro.controller;

import com.aep.doacaobooks.livro.entity.Enum.StatusLivro;
import com.aep.doacaobooks.livro.entity.Livro;
import com.aep.doacaobooks.livro.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/livro")
public class LivroController {

    private final LivroService livroService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Livro> salvarLivro(@Valid @RequestBody Livro livro){
        return ResponseEntity.ok(livroService.cadastrarLivro(livro));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> mostrarLivros(){
        return ResponseEntity.ok(livroService.todosLivros());
    }
    @GetMapping("/titulo")
    public ResponseEntity<Livro> mostrarLivrosPorTitulo(@RequestParam String titulo){
        return ResponseEntity.ok(livroService.livroPorNome(titulo));
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Livro>> mostrarLivrosDisponiveis(){
        return ResponseEntity.ok(livroService.livrosDisponiveis());
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<Livro> atualizarStataus( @PathVariable Long id,
                                                   @RequestParam StatusLivro status){
        Livro atualizado = livroService.atualizarStatus(id, status);
        return ResponseEntity.ok(atualizado);
    }
}
