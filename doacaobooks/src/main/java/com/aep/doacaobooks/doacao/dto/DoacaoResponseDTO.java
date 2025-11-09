package com.aep.doacaobooks.doacao.dto;

import com.aep.doacaobooks.doacao.entity.Enum.StatusDoacao;
import com.aep.doacaobooks.livro.dto.LivroResponseDTO;
import com.aep.doacaobooks.usuario.dto.UsuarioResponseDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DoacaoResponseDTO {
    private Long id;
    private String titulo;
    private LivroResponseDTO livro;
    private UsuarioResponseDTO beneficiario;
    private LocalDate dataAgendamento;
    private StatusDoacao statusDoacao;
}
