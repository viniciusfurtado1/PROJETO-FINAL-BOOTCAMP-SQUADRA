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
@Table(name = "TB_MUNICIPIO")
public class MunicipioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUNICIPIO_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_MUNICIPIO", allocationSize = 1, name = "MUNICIPIO_SEQ")
    @Column(name = "CODIGO_MUNICIPIO")
    private Long codigoMunicipio;

    @NotNull
    @Column(name = "CODIGO_UF")
    private Long codigoUF;

    @NotBlank
    @Size(max = 256)
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Column(name = "STATUS")
    private Integer status;
}
