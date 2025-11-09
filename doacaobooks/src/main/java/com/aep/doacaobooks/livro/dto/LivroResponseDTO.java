package com.aep.doacaobooks.livro.dto;

import com.aep.doacaobooks.livro.entity.Enum.StatusLivro;
import com.aep.doacaobooks.usuario.dto.UsuarioResponseDTO;
import lombok.Data;

@Data
public class LivroResponseDTO {
    private Long id;

    private String titulo;

    private String autor;

    private String curso;

    private String disciplina;

    private StatusLivro status;

    private UsuarioResponseDTO doador;
}
