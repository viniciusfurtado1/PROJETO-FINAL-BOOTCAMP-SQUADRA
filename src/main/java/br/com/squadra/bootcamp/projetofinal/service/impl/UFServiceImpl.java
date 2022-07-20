package br.com.squadra.bootcamp.projetofinal.service.impl;

import br.com.squadra.bootcamp.projetofinal.exception.EntidadeJaExistenteException;
import br.com.squadra.bootcamp.projetofinal.exception.EntidadeNaoExistenteException;
import br.com.squadra.bootcamp.projetofinal.model.UFModel;
import br.com.squadra.bootcamp.projetofinal.model.dto.UFDto;
import br.com.squadra.bootcamp.projetofinal.repository.UFCustomRepository;
import br.com.squadra.bootcamp.projetofinal.repository.UFRepository;
import br.com.squadra.bootcamp.projetofinal.service.UFService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UFServiceImpl implements UFService {

    private UFRepository ufRepository;

    private UFCustomRepository ufCustomRepository;

    @Transactional
    public Object cadastrarUF(UFDto ufDto) {
        verificaExistenciaUF(ufDto);

        var uf = new UFModel();
        BeanUtils.copyProperties(ufDto, uf);
        ufRepository.save(uf);
        return ufRepository.findAll();
    }

    public Object buscarUFs(Long codigoUF, String nome, String sigla, Integer status) {
        if (codigoUF != null || nome != null || sigla != null || status != null) {
            List<UFModel> ufs = ufCustomRepository.find(codigoUF, nome, sigla, status);

            if (ufs.size() == 1 && (codigoUF != null || nome != null || sigla != null)) {
                return ufs.get(0);
            } else {
                return ufCustomRepository.find(codigoUF, nome, sigla, status);
            }
        }
        return ufRepository.findAll();
    }

    @Transactional
    public Object editarUF(UFModel ufModel) {
        Optional<UFModel> ufOptional = ufRepository.findById(ufModel.getCodigoUF());

        if (ufOptional.isEmpty()) throw new EntidadeNaoExistenteException("Não existe uma UF com esse código.");

        List<UFModel> lista = ufRepository.findByNomeAndSiglaAndCodigo(ufModel.getNome(), ufModel.getSigla(), ufModel.getCodigoUF());

        var ufNovo = ufOptional.get();

        if (lista.isEmpty()) {
            BeanUtils.copyProperties(ufModel, ufNovo);
            ufRepository.save(ufNovo);
            return ufRepository.findAll();
        }
        throw new EntidadeJaExistenteException("Já existe uma UF com esse nome ou sigla");
    }

    public void verificaExistenciaUF(UFDto ufDto) {
        if (ufRepository.existsByNome(ufDto.getNome())) {
            throw new EntidadeJaExistenteException("Já existe um estado com o nome " + ufDto.getNome() + ". " +
                    "Você não pode cadastrar dois estados com o mesmo nome.");
        }
        if (ufRepository.existsBySigla(ufDto.getSigla())) {
            throw new EntidadeJaExistenteException("Já existe um estado com a sigla " + ufDto.getSigla() + ". " +
                    "Você não pode cadastrar dois estados com a mesma sigla.");
        }
    }
}