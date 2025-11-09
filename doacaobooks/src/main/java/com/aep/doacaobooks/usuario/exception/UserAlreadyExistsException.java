package com.aep.doacaobooks.usuario.exception;

import com.aep.doacaobooks.shared.ApplicationException;

public class UserAlreadyExistsException extends ApplicationException {
    public UserAlreadyExistsException(String details) {

        super(409, "Usuario já existe", details);
    }
}
