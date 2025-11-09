package com.aep.doacaobooks.doacao.controller;

import com.aep.doacaobooks.doacao.dto.DoacaoRequestDTO;
import com.aep.doacaobooks.doacao.dto.DoacaoResponseDTO;
import com.aep.doacaobooks.doacao.entity.Doacao;
import com.aep.doacaobooks.doacao.entity.Enum.StatusDoacao;
import com.aep.doacaobooks.doacao.service.DoacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doacao")
public class DoacaoController {

    private final DoacaoService doacaoService;

    @PostMapping("/doar")
    public ResponseEntity<DoacaoResponseDTO> doarLivro(@RequestBody DoacaoRequestDTO dto){
        return ResponseEntity.ok(doacaoService.doarLivro(dto));
    }

    @PostMapping("/pegar/{id}")
    public ResponseEntity<DoacaoResponseDTO> pegarLivro(@PathVariable Long id,  @RequestParam Long usuarioId){
        DoacaoResponseDTO doacao = doacaoService.pegarLivro(id, usuarioId);
        return ResponseEntity.ok(doacao);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<Doacao> atualizarStataus( @PathVariable Long id,
                                                   @RequestParam StatusDoacao status){
        Doacao atualizado = doacaoService.atualizarStatus(id, status);
        return ResponseEntity.ok(atualizado);
    }

}
