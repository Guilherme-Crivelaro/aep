package com.aep.doacaobooks.usuario.dto;

import com.aep.doacaobooks.usuario.entity.Enum.TipoUsuario;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipo;
}
