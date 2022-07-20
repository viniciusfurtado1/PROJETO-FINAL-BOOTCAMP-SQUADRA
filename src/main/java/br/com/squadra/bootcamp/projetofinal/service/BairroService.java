package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.model.BairroModel;
import br.com.squadra.bootcamp.projetofinal.model.dto.BairroDto;

public interface BairroService {
    void verificaExistenciaMunicipio(Long codigoMunicipio);

    void verificaExistenciaBairro(String nomeBairro, Long codigoMunicipio);

    Object cadastrarBairro(BairroDto bairroDto);

    Object buscarBairros(Long codigoBairro, Long codigoMunicipio, String nome, Integer status);

    Object editarBairro(BairroModel bairroModel);
}
