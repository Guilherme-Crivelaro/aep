package com.aep.doacaobooks.livro.entity;

import com.aep.doacaobooks.livro.entity.Enum.StatusLivro;
import com.aep.doacaobooks.usuario.entity.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String titulo;

    @Column(nullable = false)
    @NotNull
    private String autor;

    @Column(nullable = false)
    @NotNull
    private String curso;

    @Column(nullable = false)
    @NotNull
    private String disciplina;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private StatusLivro status;

    @ManyToOne
    @JoinColumn(name = "doador_id")
    @NotNull
    private Usuario doador;
}
