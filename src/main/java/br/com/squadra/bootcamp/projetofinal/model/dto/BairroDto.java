package br.com.squadra.bootcamp.projetofinal.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BairroDto {

    @NotNull
    private Long codigoMunicipio;

    @NotBlank
    @Size(max = 256)
    private String nome;

    @NotNull
    private Integer status;
}
