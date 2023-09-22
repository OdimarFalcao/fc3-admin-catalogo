package com.fullcycle.admin.catalogo.domain.exceptions;

import com.fullcycle.admin.catalogo.domain.validation.Error;

import java.util.List;

public class DomainException extends RuntimeException{

    private final List<Error> errors;

    protected DomainException(final List<Error> aErros) {
        super("",null,true,false);
        this.errors = aErros;
    }

    public static DomainException with(final List<Error> aErrors){
        return new DomainException(aErrors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
