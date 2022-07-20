package br.com.squadra.bootcamp.projetofinal.service;

import br.com.squadra.bootcamp.projetofinal.model.UFModel;
import br.com.squadra.bootcamp.projetofinal.model.dto.UFDto;

public interface UFService {
    void verificaExistenciaUF(UFDto ufDto);

    Object cadastrarUF(UFDto ufDto);

    Object buscarUFs(Long codigoUF, String nome, String sigla, Integer status);

    Object editarUF(UFModel ufModel);
}
