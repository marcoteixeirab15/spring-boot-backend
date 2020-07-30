package com.marcoteixeira.cursomc.resources.exception;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.marcoteixeira.cursomc.services.exceptions.AuthorizationException;
import com.marcoteixeira.cursomc.services.exceptions.DataIntegrityException;
import com.marcoteixeira.cursomc.services.exceptions.FileException;
import com.marcoteixeira.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> DataIntegrity(DataIntegrityException e, HttpServletRequest request){
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de dados", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidationError validationError = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", e.getMessage(), request.getRequestURI());

        for (FieldError x : e.getBindingResult().getFieldErrors() ){
            validationError.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorization(ObjectNotFoundException e, HttpServletRequest request){
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(standardError);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request){
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro de arquivo", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request){
        HttpStatus code = HttpStatus.valueOf(e.getStatusCode());
        StandardError standardError = new StandardError(System.currentTimeMillis(), code.value(), "Erro Amazon Service", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(code).body(standardError);
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> file(AmazonClientException e, HttpServletRequest request){
        StandardError standardError = new StandardError(System.currentTimeMillis(),  HttpStatus.BAD_REQUEST.value(), "Erro Amazon Client", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> file(AmazonS3Exception e, HttpServletRequest request){
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro S3", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

}
