package com.aep.doacaobooks.doacao.controller;

import com.aep.doacaobooks.doacao.dto.DoacaoRequestDTO;
import com.aep.doacaobooks.doacao.entity.Doacao;
import com.aep.doacaobooks.doacao.entity.Enum.StatusDoacao;
import com.aep.doacaobooks.doacao.service.DoacaoService;
import com.aep.doacaobooks.usuario.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
@RequestMapping("/doacao")
public class DoacaoController {

    private final DoacaoService doacaoService;

    @PostMapping("/doar")
    public ResponseEntity<Doacao> doarLivro(@RequestBody DoacaoRequestDTO dto){
        return ResponseEntity.ok(doacaoService.doarLivro(dto));
    }

    @PostMapping("/pegar/{id}")
    public ResponseEntity<Doacao> pegarLivro(@PathVariable Long id,  @RequestBody Usuario usuario){
        Doacao doacao = doacaoService.pegarLivro(id, usuario);
        return ResponseEntity.ok(doacao);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<Doacao> atualizarStataus( @PathVariable Long id,
                                                   @RequestParam StatusDoacao status){
        Doacao atualizado = doacaoService.atualizarStatus(id, status);
        return ResponseEntity.ok(atualizado);
    }

}
