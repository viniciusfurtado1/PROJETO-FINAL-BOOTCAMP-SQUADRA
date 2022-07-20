package br.com.squadra.bootcamp.projetofinal.exception;

public class EntidadeJaExistenteException extends RuntimeException {

    public EntidadeJaExistenteException(String mensagem) {
        super(mensagem);
    }
}
