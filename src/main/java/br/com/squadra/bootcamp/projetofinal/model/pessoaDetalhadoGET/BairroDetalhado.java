package br.com.squadra.bootcamp.projetofinal.model.pessoaDetalhadoGET;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BairroDetalhado {

    private Long codigoBairro;

    private Long codigoMunicipio;

    private String nome;

    private Integer status;

    private MunicipioDetalhado municipio;
}
