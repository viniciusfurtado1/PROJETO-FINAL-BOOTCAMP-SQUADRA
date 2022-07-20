package br.com.squadra.bootcamp.projetofinal.model.pessoaDetalhadoPUT;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PessoaInput {
    private Long codigoPessoa;
    private String nome;
    private String sobrenome;
    private Integer idade;
    private String login;
    private String senha;
    private List<EnderecoInput> enderecos;
    private Integer status;
}
