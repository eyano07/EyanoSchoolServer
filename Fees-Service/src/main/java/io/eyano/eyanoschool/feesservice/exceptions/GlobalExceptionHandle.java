package io.eyano.eyanoschool.feesservice.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorObjet> handleIdNotFoundException(IdNotFoundException e){
        log.error(e.getMessage());
        ErrorObjet errorObjet = ErrorObjet.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorCode(10)
                .date(LocalDate.now())
                .build();

        return new ResponseEntity<>(errorObjet, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IdNotNullException.class)
    public ResponseEntity<ErrorObjet> handleIdNotFoundException(IdNotNullException e){
        log.error(e.getMessage());
        ErrorObjet errorObjet = ErrorObjet.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorCode(12)
                .date(LocalDate.now())
                .build();

        return new ResponseEntity<>(errorObjet, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdIsNullException.class)
    public ResponseEntity<ErrorObjet> handleIdIsFoundException(IdIsNullException e){
        log.error(e.getMessage());
        ErrorObjet errorObjet = ErrorObjet.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorCode(12)
                .date(LocalDate.now())
                .build();

        return new ResponseEntity<>(errorObjet, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdNotFoundParamException.class)
    public ResponseEntity<ErrorObjet> handleIdNotFoundException(IdNotFoundParamException e){
        log.error(e.getMessage());
        ErrorObjet errorObjet = ErrorObjet.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorCode(10)
                .date(LocalDate.now())
                .build();
        return new ResponseEntity<>(errorObjet, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorObjet> illegalArgumentException(IllegalArgumentException e){
        log.error(e.getMessage());
        ErrorObjet errorObjet = ErrorObjet.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errorCode(20)
                .date(LocalDate.now())
                .build();

        return new ResponseEntity<>(errorObjet, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RemoveTrueException.class)
    public ResponseEntity<ErrorObjet> illegalArgumentException(RemoveTrueException e){
        log.error(e.getMessage());
        ErrorObjet errorObjet = ErrorObjet.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errorCode(30)
                .date(LocalDate.now())
                .build();

        return new ResponseEntity<>(errorObjet, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleValidationException(Exception ex) {
        log.error(ex.getMessage());
        Map<String, String> errors = new HashMap<>();

        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException e = (ConstraintViolationException)ex;
            e.getConstraintViolations().forEach(er -> {
                String fieldName = ((PathImpl) er.getPropertyPath()).getLeafNode().getName();
                String errorMessage = er.getMessage();
                errors.put(fieldName, errorMessage);
            });
        } else if (ex instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            bindingResult.getFieldErrors().forEach(e -> {
                String fieldName = e.getField();
                String errorMessage = e.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
