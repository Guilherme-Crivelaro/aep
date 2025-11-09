package com.aep.doacaobooks.doacao.exception;

import com.aep.doacaobooks.shared.ApplicationException;

public class DonationNotFoundException extends ApplicationException {
    public DonationNotFoundException(String details) {

        super(404, "Doação não encontrada", details);
    }
}
