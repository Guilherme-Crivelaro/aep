package com.aep.doacaobooks.livro.exception;

import com.aep.doacaobooks.shared.ApplicationException;

public class BookNotFoundException extends ApplicationException {
    public BookNotFoundException(String details) {
        super(404, "Livro não encontrado", details);
    }
}
