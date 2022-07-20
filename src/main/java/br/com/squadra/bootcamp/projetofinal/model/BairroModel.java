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
@Table(name = "TB_BAIRRO")
public class BairroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BAIRRO_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_BAIRRO", allocationSize = 1, name = "BAIRRO_SEQ")
    @Column(name = "CODIGO_BAIRRO")
    private Long codigoBairro;

    @NotNull
    @Column(name = "CODIGO_MUNICIPIO")
    private Long codigoMunicipio;

    @NotBlank
    @Size(max = 256)
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Column(name = "STATUS")
    private Integer status;
}
