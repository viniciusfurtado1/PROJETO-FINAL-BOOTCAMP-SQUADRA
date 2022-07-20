package br.com.squadra.bootcamp.projetofinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "TB_PESSOA")
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_PESSOA", allocationSize = 1, name = "PESSOA_SEQ")
    @Column(name = "CODIGO_PESSOA")
    private Long codigoPessoa;

    @NotBlank
    @Size(max = 256)
    @Column(name = "NOME")
    private String nome;

    @NotBlank
    @Size(max = 256)
    @Column(name = "SOBRENOME")
    private String sobrenome;

    @NotNull
    @Column(name = "IDADE")
    private Integer idade;

    @NotBlank
    @Size(max = 50)
    @Column(name = "LOGIN")
    private String login;

    @NotBlank
    @Size(max = 50)
    @Column(name = "SENHA")
    private String senha;

    @NotNull
    @Column(name = "STATUS")
    private Integer status;
}
