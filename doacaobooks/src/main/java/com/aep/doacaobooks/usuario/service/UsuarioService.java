package com.aep.doacaobooks.usuario.service;

import com.aep.doacaobooks.usuario.dto.UsuarioRequestDTO;
import com.aep.doacaobooks.usuario.dto.UsuarioResponseDTO;
import com.aep.doacaobooks.usuario.entity.Enum.TipoUsuario;
import com.aep.doacaobooks.usuario.entity.Usuario;
import com.aep.doacaobooks.usuario.exception.ConflictException;
import com.aep.doacaobooks.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> usuarioResponseDTOS = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioResponseDTO dto = new UsuarioResponseDTO();
            dto.setId(usuario.getId());
            dto.setNome(usuario.getNome());
            dto.setEmail(usuario.getEmail());
            dto.setTipo(usuario.getTipo());

            usuarioResponseDTOS.add(dto);
        }
        return usuarioResponseDTOS;
    }

    public UsuarioResponseDTO create(UsuarioRequestDTO dto){
        Boolean exist = usuarioRepository.existsByEmail(dto.getEmail());
            if (exist) {
                throw new ConflictException("Email já cadastrado");
            }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail().trim().toLowerCase());
        usuario.setSenha(dto.getSenha());
        usuario.setTipo(dto.getTipo());

        Usuario savedUsuario = usuarioRepository.save(usuario);

        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
        responseDTO.setId(savedUsuario.getId());
        responseDTO.setNome(savedUsuario.getNome());
        responseDTO.setEmail(savedUsuario.getEmail());
        responseDTO.setTipo(savedUsuario.getTipo());

        return responseDTO;
    }

    public Usuario deleteById(Long id){
        if (!usuarioRepository.existsById(id)) {
            throw new ConflictException("Usuário não existe");
        }

        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        usuarioRepository.delete(usuario);

        return usuario;
    }
}
