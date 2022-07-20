package br.com.squadra.bootcamp.projetofinal.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class PessoaDto {

    @NotBlank
    @Size(max = 256)
    private String nome;

    @NotBlank
    @Size(max = 256)
    private String sobrenome;

    @NotNull
    private Integer idade;

    @NotBlank
    @Size(max = 50)
    private String login;

    @NotBlank
    @Size(max = 50)
    private String senha;

    @NotNull
    private List<EnderecoDto> enderecos;

    @NotNull
    private Integer status;
}
