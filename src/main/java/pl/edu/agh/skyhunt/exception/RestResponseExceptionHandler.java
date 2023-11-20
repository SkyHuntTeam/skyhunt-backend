package pl.edu.agh.skyhunt.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.edu.agh.skyhunt.exception.security.InvalidAuthHeaderException;
import pl.edu.agh.skyhunt.exception.security.InvalidRegisterFormException;
import pl.edu.agh.skyhunt.exception.security.NoLoggedUserException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NoLoggedUserException.class)
    protected ResponseEntity<Object> handleAuthenticationFailure(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = InvalidRegisterFormException.class)
    protected ResponseEntity<Object> handleInvalidRegisterForm(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = InvalidAuthHeaderException.class)
    protected ResponseEntity<Object> handleInvalidAuthHeader(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
}
