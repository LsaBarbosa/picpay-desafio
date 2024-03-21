package com.santanna.picpaydesafio.infra;

import com.santanna.picpaydesafio.dto.ExceptionsDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionsDTO exceptionsDTO = new ExceptionsDTO("Usuário já está cadastrado", "400)");
        return  ResponseEntity.badRequest().body(exceptionsDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception){
        return  ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception exception){
        ExceptionsDTO exceptionsDTO = new ExceptionsDTO(exception.getMessage(), "500)");
        return  ResponseEntity.internalServerError().build();
    }

}
