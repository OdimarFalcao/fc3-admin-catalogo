package com.fullcycle.admin.catalogo.domain.exceptions;

import com.fullcycle.admin.catalogo.domain.validation.Error;

import java.util.List;

public class DomainException extends NoStackTraceException{

    private final List<Error> errors;



    protected DomainException(final String aMessage,final List<Error> aErros) {
        super(aMessage);
        this.errors = aErros;
    }

    public static DomainException with(final Error aErrors)
    {
        return new DomainException(aErrors.message(), List.of(aErrors));
    }

    public static DomainException with(final List<Error> aErrors){
        return new DomainException("",aErrors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
