package com.aep.doacaobooks.usuario.dto;

import com.aep.doacaobooks.usuario.entity.Enum.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioRequestDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipo;
}
