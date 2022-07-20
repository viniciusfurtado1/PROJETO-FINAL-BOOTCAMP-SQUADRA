package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.model.MunicipioModel;
import br.com.squadra.bootcamp.projetofinal.model.dto.MunicipioDto;

public interface MunicipioService {
    void verificaExistenciaUF(Long codigoUF);

    void verificaExistenciaMunicipio(String nomeMunicipio, Long codigoUF);

    Object cadastrarMunicipio(MunicipioDto municipioDto);

    Object buscarMunicipios(Long codigoMunicipio, Long codigoUF, String nome, Integer status);

    Object editarMunicipio(MunicipioModel municipioModel);
}
