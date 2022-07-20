package br.com.squadra.bootcamp.projetofinal.controller;

import br.com.squadra.bootcamp.projetofinal.model.MunicipioModel;
import br.com.squadra.bootcamp.projetofinal.model.dto.MunicipioDto;
import br.com.squadra.bootcamp.projetofinal.service.MunicipioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/municipio")
@AllArgsConstructor
public class MunicipioController {

    private MunicipioService municipioService;

    @PostMapping
    public ResponseEntity<Object> cadastrarMunicipio(@Valid @RequestBody MunicipioDto municipioDto) {
        return ResponseEntity.status(HttpStatus.OK).body(municipioService.cadastrarMunicipio(municipioDto));
    }

    @GetMapping
    public ResponseEntity<Object> buscarMunicipios(@RequestParam(required = false) Long codigoMunicipio,
                                                   @RequestParam(required = false) Long codigoUF,
                                                   @RequestParam(required = false) String nome,
                                                   @RequestParam(required = false) Integer status) {
        return ResponseEntity.status(HttpStatus.OK).body(municipioService.buscarMunicipios(codigoMunicipio, codigoUF, nome, status));
    }

    @PutMapping
    public ResponseEntity<Object> editarMunicipio(@Valid @RequestBody MunicipioModel municipioModel) {
        return ResponseEntity.status(HttpStatus.OK).body(municipioService.editarMunicipio(municipioModel));
    }
}
