package br.com.squadra.bootcamp.projetofinal.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UFDto {

    @NotBlank
    @Size(max = 60)
    private String nome;

    @NotBlank
    @Size(max = 3)
    private String sigla;

    @NotNull
    private Integer status;
}
