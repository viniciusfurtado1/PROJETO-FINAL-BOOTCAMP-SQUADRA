package br.com.squadra.bootcamp.projetofinal.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String campo = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getField();
        Error error = new Error();

        error.setStatus(status.value());
        error.setMensagem("O campo " + campo + " está nulo ou inválido.");
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        String parametro = ex.getName();
        String tipo = Objects.requireNonNull(ex.getRequiredType()).getSimpleName();
        String valor = (String) ex.getValue();
        Error error = new Error();

        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setMensagem(String.format("O parâmetro " + parametro + " deve ser um " + tipo + " e foi passado " + "'" + valor + "'"));
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(EntidadeJaExistenteException.class)
    protected ResponseEntity<Object> EntidadeJaExistente(EntidadeJaExistenteException ex, WebRequest request) {
        Error error = new Error();

        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setMensagem(ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(EntidadeNaoExistenteException.class)
    protected ResponseEntity<Object> EntidadeNaoExistente(EntidadeNaoExistenteException ex, WebRequest request) {
        Error error = new Error();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMensagem(ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
