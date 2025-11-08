package com.aep.doacaobooks.doacao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DoacaoRequestDTO {

    private String tituloLivro;

    private String emailBeneficiario;
}
