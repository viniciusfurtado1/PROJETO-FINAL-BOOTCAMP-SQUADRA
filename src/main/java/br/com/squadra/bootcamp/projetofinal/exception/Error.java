package br.com.squadra.bootcamp.projetofinal.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {
    private Integer status;
    private String mensagem;
}
