package com.fullcycle.admin.catalogo.domain.validation;

public abstract class Validator {

    private final ValidationHandler validationHandler;

    protected Validator(final ValidationHandler aValidationHandler){
        this.validationHandler=aValidationHandler;
    }

    public abstract void validate();

    protected ValidationHandler validationHandler(){
        return this.validationHandler;
    }

}
