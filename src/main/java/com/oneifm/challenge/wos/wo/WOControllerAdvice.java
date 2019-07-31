package com.oneifm.challenge.wos.wo;

import com.oneifm.challenge.wos.wo.exceptions.InvalidInputException;
import com.oneifm.challenge.wos.wo.model.WOError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice(assignableTypes  = WOController.class)
public class WOControllerAdvice {
    private final static Logger LOGGER = Logger.getLogger(WOControllerAdvice.class.getName());

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<WOError> handleRunTimeException(RuntimeException exception) {
        LOGGER.severe(exception.getMessage());

        return new ResponseEntity<WOError>(new WOError(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({InvalidInputException.class})
    public ResponseEntity<WOError> handleInvalidInputException(InvalidInputException exception) {
        LOGGER.severe(exception.getMessage());

        return new ResponseEntity<WOError>(new WOError(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
