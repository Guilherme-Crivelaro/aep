package com.aep.doacaobooks.livro.controller;

import com.aep.doacaobooks.livro.dto.LivroRequestDTO;
import com.aep.doacaobooks.livro.dto.LivroResponseDTO;
import com.aep.doacaobooks.livro.entity.Enum.StatusLivro;
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
    public ResponseEntity<LivroResponseDTO> salvarLivro(@Valid @RequestBody LivroRequestDTO livroDTO){
        return ResponseEntity.ok(livroService.cadastrarLivro(livroDTO));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> mostrarLivros(){
        return ResponseEntity.ok(livroService.todosLivros());
    }
    @GetMapping("/titulo")
    public ResponseEntity<Livro> mostrarLivrosPorTitulo(@RequestParam String titulo){
        return ResponseEntity.ok(livroService.livroPorNome(titulo));
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<LivroResponseDTO>> mostrarLivrosDisponiveis(){
        return ResponseEntity.ok(livroService.livrosDisponiveis());
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<LivroResponseDTO> atualizarStataus( @PathVariable Long id,
                                                   @RequestParam StatusLivro status){
        LivroResponseDTO atualizado = livroService.atualizarStatus(id, status);
        return ResponseEntity.ok(atualizado);
    }
}
