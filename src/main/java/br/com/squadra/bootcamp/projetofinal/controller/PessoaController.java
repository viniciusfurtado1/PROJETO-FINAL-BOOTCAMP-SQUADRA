package br.com.squadra.bootcamp.projetofinal.controller;

import br.com.squadra.bootcamp.projetofinal.model.dto.PessoaDto;
import br.com.squadra.bootcamp.projetofinal.model.pessoaDetalhadoPUT.PessoaInput;
import br.com.squadra.bootcamp.projetofinal.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
public class PessoaController {

    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Object> cadastrarPessoa(@Valid @RequestBody PessoaDto pessoaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.cadastrarPessoa(pessoaDto));
    }

    @GetMapping
    public ResponseEntity<Object> buscarPessoas(@RequestParam(required = false) Long codigoPessoa,
                                                @RequestParam(required = false) String login,
                                                @RequestParam(required = false) Integer status) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarPessoas(codigoPessoa, login, status));
    }

    @PutMapping
    public ResponseEntity<Object> editarPessoa(@Valid @RequestBody PessoaInput pessoaInput) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.editarPessoa(pessoaInput));
    }
}
