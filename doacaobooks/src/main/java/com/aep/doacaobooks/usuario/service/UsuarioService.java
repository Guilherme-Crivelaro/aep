package com.aep.doacaobooks.usuario.service;

import com.aep.doacaobooks.usuario.dto.UsuarioRequestDTO;
import com.aep.doacaobooks.usuario.dto.UsuarioResponseDTO;
import com.aep.doacaobooks.usuario.entity.Usuario;
import com.aep.doacaobooks.usuario.exception.UserAlreadyExistsException;
import com.aep.doacaobooks.usuario.exception.UserNotFoundException;
import com.aep.doacaobooks.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public List<UsuarioResponseDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> usuarioResponseDTOS = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioResponseDTO dto = modelMapper.map(usuario, UsuarioResponseDTO.class);
            usuarioResponseDTOS.add(dto);
        }
        return usuarioResponseDTOS;
    }

    public UsuarioResponseDTO create(UsuarioRequestDTO dto) {
        Boolean exist = usuarioRepository.existsByEmail(dto.getEmail());
        if (exist) {
            throw new UserAlreadyExistsException("email: " + dto.getEmail());
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail().trim().toLowerCase());
        usuario.setSenha(dto.getSenha());
        usuario.setTipo(dto.getTipo());

        Usuario savedUsuario = usuarioRepository.save(usuario);

        return modelMapper.map(savedUsuario, UsuarioResponseDTO.class);
    }

    public Usuario deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UserNotFoundException("id: " + id);
        }

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id: " + id));

        usuarioRepository.delete(usuario);

        return usuario;
    }
}
