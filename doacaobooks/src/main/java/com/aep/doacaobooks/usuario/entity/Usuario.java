package com.aep.doacaobooks.usuario.entity;

import com.aep.doacaobooks.usuario.entity.Enum.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotBlank
    private String nome;
    @Email
    private String email;
    @NotBlank
    @Size(min = 8)
    private String senha;
    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoUsuario tipo;


}
