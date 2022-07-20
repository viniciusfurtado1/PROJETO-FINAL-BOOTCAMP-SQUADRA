package br.com.squadra.bootcamp.projetofinal.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EnderecoDto {

    @NotNull
    private Long codigoBairro;

    @Size(max = 256)
    @NotBlank
    private String nomeRua;

    @Size(max = 10)
    @NotBlank
    private String numero;

    @Size(max = 20)
    @NotBlank
    private String complemento;

    @Size(max = 10)
    @NotBlank
    private String cep;
}
