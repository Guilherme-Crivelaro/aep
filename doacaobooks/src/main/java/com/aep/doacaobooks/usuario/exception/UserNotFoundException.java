package com.aep.doacaobooks.usuario.exception;

import com.aep.doacaobooks.shared.ApplicationException;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException(String details) {
        super(404, "Usuario não encontrado", details);
    }
}
