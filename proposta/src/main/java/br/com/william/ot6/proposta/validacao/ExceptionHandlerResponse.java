package br.com.william.ot6.proposta.validacao;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandlerResponse {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorList> exception(MethodArgumentNotValidException exception){

        ErrorList errors = new ErrorList(HttpStatus.BAD_REQUEST.value());



        exception.getBindingResult().getFieldErrors().forEach(err ->
                errors.addFieldError(new FieldError(err.getField(), err.getDefaultMessage())));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorList> unprocessable(APIException exception){

        ErrorList errors = new ErrorList(HttpStatus.UNPROCESSABLE_ENTITY.value());

        errors.addFieldError(new FieldError(exception.getField(), exception.getMessage()));

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorList> exception(ConstraintViolationException exception){

        ErrorList errors = new ErrorList(HttpStatus.BAD_REQUEST.value());

        exception.getConstraintViolations().forEach(err ->
                errors.addFieldError(new FieldError(err.getInvalidValue().toString(), err.getMessage())));

        return ResponseEntity.badRequest().body(errors);
    }

}
