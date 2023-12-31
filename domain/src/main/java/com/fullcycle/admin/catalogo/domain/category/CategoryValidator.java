package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.validation.Error;
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler;
import com.fullcycle.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {
    private final Category category;
    public CategoryValidator(final Category category, ValidationHandler aValidationHandler) {
        super(aValidationHandler);
        this.category = category;
    }

    @Override
    public void validate() {
        if (this.category.getName() == null){
            this.validationHandler().append(new Error("`name` should not be null"));
        }
    }
}
