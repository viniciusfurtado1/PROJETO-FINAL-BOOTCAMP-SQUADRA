package br.com.squadra.bootcamp.projetofinal.model.pessoaDetalhadoPUT;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {
    private Long codigoEndereco;
    private Long codigoBairro;
    private Long codigoPessoa;
    private String nomeRua;
    private String numero;
    private String complemento;
    private String cep;
}
