package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.model.dto.PessoaDto;
import br.com.squadra.bootcamp.projetofinal.model.pessoaDetalhadoPUT.PessoaInput;

public interface PessoaService {
    Object cadastrarPessoa(PessoaDto pessoaDto);

    Object buscarPessoas(Long codigoPessoa, String login, Integer status);

    Object editarPessoa(PessoaInput pessoaInput);

    void verificaExistenciaBairro(Long codigoBairro);

    void verificaExistenciaEndereco(PessoaDto pessoaDto);

}
