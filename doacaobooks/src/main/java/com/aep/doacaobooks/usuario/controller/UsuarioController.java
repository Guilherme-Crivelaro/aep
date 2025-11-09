package com.aep.doacaobooks.usuario.controller;


import com.aep.doacaobooks.usuario.dto.UsuarioRequestDTO;
import com.aep.doacaobooks.usuario.dto.UsuarioResponseDTO;
import com.aep.doacaobooks.usuario.entity.Usuario;
import com.aep.doacaobooks.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@ControllerAdvice
@RequestMapping("/usuario")
@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> get() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(usuarioService.create(dto));
    }
}
