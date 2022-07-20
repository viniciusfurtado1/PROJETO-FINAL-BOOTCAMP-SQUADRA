package br.com.squadra.bootcamp.projetofinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "TB_ENDERECO")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENDERECO_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_ENDERECO", allocationSize = 1, name = "ENDERECO_SEQ")
    @Column(name = "CODIGO_ENDERECO")
    private Long codigoEndereco;

    @Column(name = "CODIGO_PESSOA")
    private Long codigoPessoa;

    @Column(name = "CODIGO_BAIRRO")
    private Long codigoBairro;

    @NotBlank
    @Size(max = 256)
    @Column(name = "NOME_RUA")
    private String nomeRua;

    @NotBlank
    @Size(max = 10)
    @Column(name = "NUMERO")
    private String numero;

    @Size(max = 20)
    @Column(name = "COMPLEMENTO")
    private String complemento;

    @NotBlank
    @Size(max = 10)
    @Column(name = "CEP")
    private String cep;

    @Override
    public boolean equals(Object obj) {
        return this.getCodigoEndereco().equals(((EnderecoModel) obj).getCodigoEndereco());
    }
}
