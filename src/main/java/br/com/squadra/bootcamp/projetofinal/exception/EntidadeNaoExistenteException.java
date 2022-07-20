package br.com.squadra.bootcamp.projetofinal.exception;

public class EntidadeNaoExistenteException extends RuntimeException {

    public EntidadeNaoExistenteException(String mensagem) {
        super(mensagem);
    }
}
