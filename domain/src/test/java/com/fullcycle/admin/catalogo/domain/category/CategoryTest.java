package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory(){

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assitida";
        final var expectedIsActive = true;

        final var atualCategory = Category.newCategory(expectedName,expectedDescription,expectedIsActive);

        Assertions.assertNotNull(atualCategory);
        Assertions.assertNotNull(atualCategory.getId());
        Assertions.assertEquals(expectedDescription,atualCategory.getDescription());
        Assertions.assertEquals(expectedName,atualCategory.getName());
        Assertions.assertEquals(expectedIsActive,atualCategory.isActive());
        Assertions.assertNotNull(atualCategory.getCreatedAt());
        Assertions.assertNotNull(atualCategory.getUpdateAt());
        Assertions.assertNull(atualCategory.getDeleteAt());

    }

    @Test
    public void givenAInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final String expectedName = null;
        final var expectedErrorCount = 1 ;
        final var expectedErrorMessage = "`name` should not be null" ;
        final var expectedDescription = "A categoria mais assitida";
        final var expectedIsActive = true;

        final var atualCategory = Category.newCategory(expectedName,expectedDescription,expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> atualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final String expectedName = " ";
        final var expectedErrorCount = 1 ;
        final var expectedErrorMessage = "`name` should not be empty" ;
        final var expectedDescription = "A categoria mais assitida";
        final var expectedIsActive = true;

        final var atualCategory = Category.newCategory(expectedName,expectedDescription,expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> atualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final String expectedName = "fi ";
        final var expectedErrorCount = 1 ;
        final var expectedErrorMessage = "`name` must be between 3 and 255 characters" ;
        final var expectedDescription = "A categoria mais assitida";
        final var expectedIsActive = true;

        final var atualCategory = Category.newCategory(expectedName,expectedDescription,expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> atualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidNameLengthMoreThan255_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final String expectedName = "As experiências acumuladas demonstram que a consolidação das estruturas talvez venha a ressaltar a relatividade das condições financeiras e administrativas exigidas. " +
                "É importante questionar o quanto a contínua expansão de nossa atividade faz parte de um processo de gerenciamento do remanejamento dos quadros funcionais. " +
                "Desta maneira, a mobilidade dos capitais internacionais agrega valor ao estabelecimento do investimento em reciclagem técnica. ";
        final var expectedErrorCount = 1 ;
        final var expectedErrorMessage = "`name` must be between 3 and 255 characters" ;
        final var expectedDescription = "A categoria mais assitida";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName,expectedDescription,expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage,actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidEmptyDescriptionParams_whenCallNewCategory_thenInstantiateACategory(){

        final var expectedName = "Filmes";
        final var expectedDescription = " ";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName,expectedDescription,expectedIsActive);


        Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName,actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdateAt());
        Assertions.assertNull(actualCategory.getDeleteAt());

    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCategory_thenInstantiateACategory(){

        final var expectedName = "Filmes";
        final var expectedDescription = " A categoria mais assistida";
        final var expectedIsActive = false;

        final var actualCategory = Category.newCategory(expectedName,expectedDescription,expectedIsActive);


        Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName,actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdateAt());
        Assertions.assertNotNull(actualCategory.getDeleteAt());

    }

    @Test
    public void givenAValidInactiveCategpry_whenCallActivate_thenReturnCategoryActived(){
        final var expectedName = "Filmes";
        final var expectedDescription = " A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory(expectedName,expectedDescription,false);

        final var createdAT = aCategory.getCreatedAt();
        final var updatedAT = aCategory.getUpdateAt();

        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getDeleteAt());

        final var actualCategory = aCategory.activate();

        Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId(),actualCategory.getId());
        Assertions.assertEquals(expectedName,actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.isActive());
        Assertions.assertEquals(createdAT,actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdateAt().isAfter(updatedAT));
        Assertions.assertNull(actualCategory.getDeleteAt());

    }
    @Test
    public void givenAValidActiveCategpry_whenCallDeactivate_thenReturnCategoryInactived(){
        final var expectedName = "Filmes";
        final var expectedDescription = " A categoria mais assistida";
        final var expectedIsActive = false;

        final var aCategory = Category.newCategory(expectedName,expectedDescription,true);

        final var createdAT = aCategory.getCreatedAt();
        final var updatedAT = aCategory.getUpdateAt();

        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeleteAt());

         final var actualCategory = aCategory.deactivate();

        Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId(),actualCategory.getId());
        Assertions.assertEquals(expectedName,actualCategory.getName());
        Assertions.assertEquals(expectedDescription,actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive,actualCategory.isActive());
        Assertions.assertEquals(createdAT,actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdateAt().isAfter(updatedAT));
        Assertions.assertNotNull(actualCategory.getDeleteAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory =
                Category.newCategory("Film", "A categoria", expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdateAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdateAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeleteAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdateToInactive_thenReturnCategoryUpdated() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var aCategory =
                Category.newCategory("Film", "A categoria", true);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeleteAt());

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdateAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getDeleteAt());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdateAt().isAfter(updatedAt));
    }


    @Test
    public void givenAValidCategory_whenCallUpdateWithInvalidParams_thenReturnCategoryUpdated() {
        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory =
                Category.newCategory("Filmes", "A categoria", expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdateAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdateAt().isAfter(updatedAt));
        Assertions.assertNull(aCategory.getDeleteAt());
    }

}
