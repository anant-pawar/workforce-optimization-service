package com.oneifm.challenge.wos.wo;

import com.oneifm.challenge.wos.wo.exceptions.InvalidInputException;
import com.oneifm.challenge.wos.wo.model.WOError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes  = WOController.class)
public class WOControllerAdvice {
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleRunTimeException(RuntimeException exception) {
        return new ResponseEntity<WOError>(new WOError(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({InvalidInputException.class})
    public ResponseEntity<?> handleInvalidInputException(InvalidInputException exception) {
        return new ResponseEntity<WOError>(new WOError(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
