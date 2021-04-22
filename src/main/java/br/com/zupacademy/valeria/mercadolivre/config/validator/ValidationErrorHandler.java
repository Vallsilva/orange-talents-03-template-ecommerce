package br.com.zupacademy.valeria.mercadolivre.config.validator;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    private final MessageSource messageSource;


    public ValidationErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ValidatorErrorsDTO handle(BindException exception){
        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return builValidationErrors(globalErrors, fieldErrors);
    }

    private ValidatorErrorsDTO builValidationErrors(List<ObjectError> globalErrors, List<FieldError> fieldErrors){
        ValidatorErrorsDTO validatorErrors = new ValidatorErrorsDTO();

        globalErrors.forEach(error -> validatorErrors.addError(getErrorMessage(error)));
        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            validatorErrors.addFielError(error.getField(), errorMessage);
        });
        return validatorErrors;
    }

    private String getErrorMessage(ObjectError error){
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}
