package com.aep.doacaobooks.doacao.entity;

import com.aep.doacaobooks.doacao.entity.Enum.StatusDoacao;
import com.aep.doacaobooks.livro.entity.Livro;
import com.aep.doacaobooks.usuario.entity.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "doacoes")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "beneficiario_id")
    private Usuario beneficiario;

    private LocalDate dataAgendamento;

    @Enumerated(EnumType.STRING)
    private StatusDoacao statusDoacao;

}
