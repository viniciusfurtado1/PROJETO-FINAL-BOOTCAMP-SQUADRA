package br.com.squadra.bootcamp.projetofinal.model.pessoaDetalhadoGET;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PessoaDetalhado {

    private Long codigoPessoa;

    private String nome;

    private String sobrenome;

    private Integer idade;

    private String login;

    private String senha;

    private List<EnderecoDetalhado> enderecos;

    private Integer status;
}
