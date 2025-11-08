package com.aep.doacaobooks.usuario.service;

import com.aep.doacaobooks.usuario.entity.Usuario;
import com.aep.doacaobooks.usuario.exception.ConflictException;
import com.aep.doacaobooks.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario create(Usuario usuario){
        Boolean exist = usuarioRepository.existsByEmail(usuario.getEmail());
            if (exist) {
                throw new ConflictException("Email já cadastrado");
            }
            return usuarioRepository.save(usuario);
    }




}
