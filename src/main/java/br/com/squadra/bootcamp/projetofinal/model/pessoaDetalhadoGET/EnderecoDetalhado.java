package br.com.squadra.bootcamp.projetofinal.model.pessoaDetalhadoGET;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDetalhado {

    private Long codigoEndereco;

    private Long codigoBairro;

    private Long codigoPessoa;

    private String nomeRua;

    private String numero;

    private String complemento;

    private String cep;

    private BairroDetalhado bairro;
}
