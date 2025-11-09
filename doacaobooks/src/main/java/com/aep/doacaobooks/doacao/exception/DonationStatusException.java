package com.aep.doacaobooks.doacao.exception;

import com.aep.doacaobooks.shared.ApplicationException;

public class DonationStatusException extends ApplicationException {
    public DonationStatusException(String details) {
        super(409, "Este livro não está disponível", details);
    }
}
