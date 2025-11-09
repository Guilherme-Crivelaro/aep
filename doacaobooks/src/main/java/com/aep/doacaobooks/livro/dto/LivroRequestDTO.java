package com.aep.doacaobooks.livro.dto;

import com.aep.doacaobooks.livro.entity.Enum.StatusLivro;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LivroRequestDTO {

    @NotNull
    private String titulo;

    @NotNull
    private String autor;

    @NotNull
    private String curso;

    @NotNull
    private String disciplina;

    @NotNull
    private StatusLivro status;

    @NotNull
    private Long doadorId;
}
