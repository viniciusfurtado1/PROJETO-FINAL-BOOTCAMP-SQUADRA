package br.com.squadra.bootcamp.projetofinal.service.impl;

import br.com.squadra.bootcamp.projetofinal.exception.EntidadeJaExistenteException;
import br.com.squadra.bootcamp.projetofinal.exception.EntidadeNaoExistenteException;
import br.com.squadra.bootcamp.projetofinal.model.*;
import br.com.squadra.bootcamp.projetofinal.model.dto.PessoaDto;
import br.com.squadra.bootcamp.projetofinal.model.pessoaDetalhadoGET.*;
import br.com.squadra.bootcamp.projetofinal.model.pessoaDetalhadoPUT.PessoaInput;
import br.com.squadra.bootcamp.projetofinal.repository.*;
import br.com.squadra.bootcamp.projetofinal.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private PessoaRepository pessoaRepository;

    private EnderecoRepository enderecoRepository;

    private BairroRepository bairroRepository;

    private MunicipioRepository municipioRepository;

    private UFRepository ufRepository;

    private PessoaCustomRepository pessoaCustomRepository;

    @Transactional
    public Object cadastrarPessoa(PessoaDto pessoaDto) {
        var pessoa = new PessoaModel();
        BeanUtils.copyProperties(pessoaDto, pessoa);

        verificaExistenciaLogin(pessoaDto);

        PessoaModel pessoaModel = pessoaRepository.save(pessoa);

        verificaExistenciaEndereco(pessoaDto);

        List<EnderecoModel> enderecos = pessoaDto.getEnderecos().stream().map(enderecoDto -> {
            var endereco = new EnderecoModel();
            BeanUtils.copyProperties(enderecoDto, endereco);
            verificaExistenciaBairro(endereco.getCodigoBairro());
            endereco.setCodigoPessoa(pessoaModel.getCodigoPessoa());
            return endereco;
        }).toList();

        enderecoRepository.saveAll(enderecos);

        return pessoaRepository.findAll();
    }

    public Object buscarPessoas(Long codigoPessoa, String login, Integer status) {
        if (codigoPessoa != null || login != null || status != null) {
            List<PessoaModel> pessoaModels = pessoaCustomRepository.find(codigoPessoa, login, status);

            if (pessoaModels.size() == 1 && codigoPessoa != null) {
                return getPessoaDetalhado(pessoaModels.get(0).getCodigoPessoa());
            } else {
                return pessoaCustomRepository.find(codigoPessoa, login, status);
            }
        }
        return pessoaRepository.findAll();
    }


    @Transactional
    public Object editarPessoa(PessoaInput pessoaInput) {
        Optional<PessoaModel> pessoaOptional = pessoaRepository.findById(pessoaInput.getCodigoPessoa());
        if (pessoaOptional.isPresent()) {

            BeanUtils.copyProperties(pessoaInput, pessoaOptional.get());
            pessoaRepository.save(pessoaOptional.get());

            List<EnderecoModel> enderecosPassados = pessoaInput.getEnderecos().stream().map(enderecoInput -> {
                var endereco = new EnderecoModel();
                endereco.setCodigoEndereco(enderecoInput.getCodigoEndereco());
                BeanUtils.copyProperties(enderecoInput, endereco);
                return endereco;
            }).toList();

            List<EnderecoModel> enderecosNoBanco = enderecoRepository.findByCodigoPessoa(pessoaInput.getCodigoPessoa());

            List<EnderecoModel> enderecoExcluido = enderecosNoBanco.stream()
                    .filter(enderecoModel -> !enderecosPassados.contains(enderecoModel)).toList();

            enderecoRepository.deleteAll(enderecoExcluido);
            enderecoRepository.saveAll(enderecosPassados);
        }

        return pessoaRepository.findAll();
    }

    private PessoaDetalhado getPessoaDetalhado(Long codigoPessoa) {
        var pessoaOptional = pessoaRepository.findById(codigoPessoa);

        if (pessoaOptional.isEmpty())
            throw new EntidadeNaoExistenteException("Não existe uma pessoa com esse código.");

        var pessoaDetalhado = new PessoaDetalhado();
        BeanUtils.copyProperties(pessoaOptional.get(), pessoaDetalhado);
        pessoaDetalhado.setCodigoPessoa(codigoPessoa);

        List<EnderecoModel> enderecos = enderecoRepository.findByCodigoPessoa(codigoPessoa);

        pessoaDetalhado.setEnderecos(enderecos.stream().map(enderecoModel -> {
            var enderecoDetalhado = new EnderecoDetalhado();
            BeanUtils.copyProperties(enderecoModel, enderecoDetalhado);

            var bairroDetalhado = new BairroDetalhado();
            BairroModel bairroModel = bairroRepository.findById(enderecoDetalhado.getCodigoBairro()).get();
            BeanUtils.copyProperties(bairroModel, bairroDetalhado);
            enderecoDetalhado.setBairro(bairroDetalhado);

            var MunicipioDetalhado = new MunicipioDetalhado();
            MunicipioModel municipioModel = municipioRepository.findById(bairroModel.getCodigoMunicipio()).get();
            BeanUtils.copyProperties(municipioModel, MunicipioDetalhado);
            bairroDetalhado.setMunicipio(MunicipioDetalhado);

            var UFDetalhado = new UFDetalhado();
            UFModel UFModel = ufRepository.findById(municipioModel.getCodigoUF()).get();
            BeanUtils.copyProperties(UFModel, UFDetalhado);
            MunicipioDetalhado.setUf(UFDetalhado);

            return enderecoDetalhado;
        }).toList());

        return pessoaDetalhado;
    }

    public void verificaExistenciaBairro(Long codigoBairro) {
        if (!bairroRepository.existsById(codigoBairro)) {
            throw new EntidadeJaExistenteException("Não existe um bairro com esse código");
        }
    }

    public void verificaExistenciaEndereco(PessoaDto pessoaDto) {
        if (pessoaDto.getEnderecos().isEmpty()) {
            throw new EntidadeNaoExistenteException("Impossível cadastrar pessoa sem endereço.");
        }
    }

    private void verificaExistenciaLogin(PessoaDto pessoaDto) {
        if (pessoaRepository.existsByLogin(pessoaDto.getLogin())) {
            throw new EntidadeJaExistenteException("Já existe uma pessoa com esse login.");
        }
    }
}
