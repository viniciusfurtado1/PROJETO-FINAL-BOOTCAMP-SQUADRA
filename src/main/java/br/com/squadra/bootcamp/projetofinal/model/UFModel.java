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
@Table(name = "TB_UF")
public class UFModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UF_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_UF", allocationSize = 1, name = "UF_SEQ")
    @Column(name = "CODIGO_UF")
    private Long codigoUF;

    @NotBlank
    @Size(max = 60)
    @Column(name = "NOME")
    private String nome;

    @NotBlank
    @Size(max = 3)
    @Column(name = "SIGLA")
    private String sigla;

    @NotNull
    @Column(name = "STATUS")
    private Integer status;
}
