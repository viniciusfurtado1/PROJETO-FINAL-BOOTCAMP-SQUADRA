package br.com.squadra.bootcamp.projetofinal.service.impl;

import br.com.squadra.bootcamp.projetofinal.exception.EntidadeJaExistenteException;
import br.com.squadra.bootcamp.projetofinal.exception.EntidadeNaoExistenteException;
import br.com.squadra.bootcamp.projetofinal.model.BairroModel;
import br.com.squadra.bootcamp.projetofinal.model.dto.BairroDto;
import br.com.squadra.bootcamp.projetofinal.repository.BairroCustomRepository;
import br.com.squadra.bootcamp.projetofinal.repository.BairroRepository;
import br.com.squadra.bootcamp.projetofinal.repository.MunicipioRepository;
import br.com.squadra.bootcamp.projetofinal.service.BairroService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BairroServiceImpl implements BairroService {

    private BairroRepository bairroRepository;

    private BairroCustomRepository bairroCustomRepository;

    private MunicipioRepository municipioRepository;

    @Transactional
    public Object cadastrarBairro(BairroDto bairroDto) {
        verificaExistenciaMunicipio(bairroDto.getCodigoMunicipio());
        verificaExistenciaBairro(bairroDto.getNome(), bairroDto.getCodigoMunicipio());

        var bairro = new BairroModel();
        BeanUtils.copyProperties(bairroDto, bairro);
        bairroRepository.save(bairro);
        return bairroRepository.findAll();
    }

    public Object buscarBairros(Long codigoBairro, Long codigoMunicipio, String nome, Integer status) {
        if (codigoBairro != null || codigoMunicipio != null || nome != null || status != null) {
            List<BairroModel> ufs = bairroCustomRepository.find(codigoBairro, codigoMunicipio, nome, status);
            if (ufs.size() == 1 && codigoBairro != null) {
                return ufs.get(0);
            } else {
                return bairroCustomRepository.find(codigoBairro, codigoMunicipio, nome, status);
            }
        }
        return bairroRepository.findAll();
    }

    @Transactional
    public Object editarBairro(BairroModel bairroModel) {
        Optional<BairroModel> bairroOptional = bairroRepository.findById(bairroModel.getCodigoBairro());

        if (bairroOptional.isEmpty()) throw new EntidadeNaoExistenteException("Não existe um bairro com esse código.");

        verificaExistenciaMunicipio(bairroModel.getCodigoMunicipio());
        var bairroNovo = bairroOptional.get();
        BeanUtils.copyProperties(bairroModel, bairroNovo);
        bairroRepository.save(bairroNovo);
        return bairroRepository.findAll();
    }

    public void verificaExistenciaMunicipio(Long codigoMunicipio) {
        if (!municipioRepository.existsById(codigoMunicipio)) {
            throw new EntidadeNaoExistenteException("Não existe um município com esse código.");
        }
    }

    public void verificaExistenciaBairro(String nomeBairro, Long codigoMunicipio) {
        if (bairroRepository.existsByNomeAndCodigoMunicipio(nomeBairro, codigoMunicipio)) {
            throw new EntidadeJaExistenteException("Já existe um bairro com esse nome nesse município.");
        }
    }
}
