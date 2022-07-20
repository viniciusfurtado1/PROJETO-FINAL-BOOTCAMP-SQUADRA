package br.com.squadra.bootcamp.projetofinal.controller;

import br.com.squadra.bootcamp.projetofinal.model.BairroModel;
import br.com.squadra.bootcamp.projetofinal.model.dto.BairroDto;
import br.com.squadra.bootcamp.projetofinal.service.BairroService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bairro")
@AllArgsConstructor
public class BairroController {

    private BairroService bairroService;

    @PostMapping
    public ResponseEntity<Object> cadastrarBairro(@Valid @RequestBody BairroDto bairroDto) {
        return ResponseEntity.status(HttpStatus.OK).body(bairroService.cadastrarBairro(bairroDto));
    }

    @GetMapping
    public ResponseEntity<Object> buscarBairros(@RequestParam(required = false) Long codigoBairro,
                                                @RequestParam(required = false) Long codigoMunicipio,
                                                @RequestParam(required = false) String nome,
                                                @RequestParam(required = false) Integer status) {
        return ResponseEntity.status(HttpStatus.OK).body(bairroService.buscarBairros(codigoBairro, codigoMunicipio, nome, status));
    }

    @PutMapping
    public ResponseEntity<Object> editarBairro(@Valid @RequestBody BairroModel bairroModel) {
        return ResponseEntity.status(HttpStatus.OK).body(bairroService.editarBairro(bairroModel));
    }
}
