package br.com.squadra.bootcamp.projetofinal.service.impl;

import br.com.squadra.bootcamp.projetofinal.exception.EntidadeJaExistenteException;
import br.com.squadra.bootcamp.projetofinal.exception.EntidadeNaoExistenteException;
import br.com.squadra.bootcamp.projetofinal.model.MunicipioModel;
import br.com.squadra.bootcamp.projetofinal.model.dto.MunicipioDto;
import br.com.squadra.bootcamp.projetofinal.repository.MunicipioCustomRepository;
import br.com.squadra.bootcamp.projetofinal.repository.MunicipioRepository;
import br.com.squadra.bootcamp.projetofinal.repository.UFRepository;
import br.com.squadra.bootcamp.projetofinal.service.MunicipioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MunicipioServiceImpl implements MunicipioService {

    private MunicipioRepository municipioRepository;

    private MunicipioCustomRepository municipioCustomRepository;

    private UFRepository ufRepository;

    @Transactional
    public Object cadastrarMunicipio(MunicipioDto municipioDto) {
        verificaExistenciaUF(municipioDto.getCodigoUF());
        verificaExistenciaMunicipio(municipioDto.getNome() , municipioDto.getCodigoUF());

        var municipio = new MunicipioModel();
        BeanUtils.copyProperties(municipioDto, municipio);
        municipioRepository.save(municipio);
        return municipioRepository.findAll();
    }

    public Object buscarMunicipios(Long codigoMunicipio, Long codigoUF, String nome, Integer status) {
        if (codigoMunicipio != null || codigoUF != null || nome != null || status != null) {
            List<MunicipioModel> ufs = municipioCustomRepository.find(codigoMunicipio, codigoUF, nome, status);
            if (ufs.size() == 1 && codigoMunicipio != null) {
                return ufs.get(0);
            } else {
                return municipioCustomRepository.find(codigoMunicipio, codigoUF, nome, status);
            }
        }
        return municipioRepository.findAll();
    }

    @Transactional
    public Object editarMunicipio(MunicipioModel municipioModel) {
        Optional<MunicipioModel> municipioOptional = municipioRepository.findById(municipioModel.getCodigoMunicipio());

        if (municipioOptional.isEmpty())
            throw new EntidadeNaoExistenteException("Não existe um município com esse código.");

        verificaExistenciaUF(municipioModel.getCodigoUF());
        var municipioNovo = municipioOptional.get();
        BeanUtils.copyProperties(municipioModel, municipioNovo);
        municipioRepository.save(municipioNovo);
        return municipioRepository.findAll();
    }

    public void verificaExistenciaUF(Long codigoUF) {
        if (!ufRepository.existsById(codigoUF)) {
            throw new EntidadeNaoExistenteException("Não existe uma UF com esse código.");
        }
    }

    public void verificaExistenciaMunicipio(String nomeMunicipio, Long codigoUF) {
        if (municipioRepository.existsByNomeAndCodigoUF(nomeMunicipio, codigoUF)) {
            throw new EntidadeJaExistenteException("Já existe um município com esse nome nessa UF.");
        }
    }
}
