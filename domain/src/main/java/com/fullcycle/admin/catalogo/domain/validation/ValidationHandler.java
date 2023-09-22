package com.fullcycle.admin.catalogo.domain.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(Error anError);
    ValidationHandler append(ValidationHandler anHandle);
    ValidationHandler validate(Validation aValidation);

    List<Error> getErros();
    default boolean hasErros(){
        return getErros() != null && !getErros().isEmpty();
    }

    public interface Validation{
        void validate();
    }
}
