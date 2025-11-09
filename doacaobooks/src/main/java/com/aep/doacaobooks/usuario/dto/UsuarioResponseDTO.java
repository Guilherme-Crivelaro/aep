package com.aep.doacaobooks.usuario.dto;

import com.aep.doacaobooks.usuario.entity.Enum.TipoUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipo;
}
