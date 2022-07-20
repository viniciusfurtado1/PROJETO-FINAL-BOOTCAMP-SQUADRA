package br.com.squadra.bootcamp.projetofinal.model.pessoaDetalhadoGET;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MunicipioDetalhado {

    private Long codigoMunicipio;

    private Long codigoUF;

    private String nome;

    private Integer status;

    private UFDetalhado uf;
}
