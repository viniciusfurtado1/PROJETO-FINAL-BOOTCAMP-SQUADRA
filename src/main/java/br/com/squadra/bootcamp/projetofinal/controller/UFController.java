package br.com.squadra.bootcamp.projetofinal.controller;

import br.com.squadra.bootcamp.projetofinal.model.UFModel;
import br.com.squadra.bootcamp.projetofinal.model.dto.UFDto;
import br.com.squadra.bootcamp.projetofinal.service.UFService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/uf")
@AllArgsConstructor
public class UFController {

    private UFService ufService;

    @PostMapping
    public ResponseEntity<Object> cadastrarUF(@Valid @RequestBody UFDto ufDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ufService.cadastrarUF(ufDto));
    }

    @GetMapping
    public ResponseEntity<Object> buscarUFs(@RequestParam(required = false) Long codigoUF,
                                            @RequestParam(required = false) String nome,
                                            @RequestParam(required = false) String sigla,
                                            @RequestParam(required = false) Integer status) {
        return ResponseEntity.status(HttpStatus.OK).body(ufService.buscarUFs(codigoUF, nome, sigla, status));
    }

    @PutMapping
    public ResponseEntity<Object> editarUF(@Valid @RequestBody UFModel ufModel) {
        return ResponseEntity.status(HttpStatus.OK).body(ufService.editarUF(ufModel));
    }
}
