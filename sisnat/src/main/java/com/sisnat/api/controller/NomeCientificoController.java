package com.sisnat.api.controller;

import com.sisnat.api.app.nomeCientifico.NomeCientificoApp;
import com.sisnat.api.app.nomeCientifico.dto.NomeCientificoDTO;
import com.sisnat.api.app.nomeCientifico.dto.NomeCientificoFindDTO;
import com.sisnat.api.app.nomeCientifico.dto.NomeCientificoInputDTO;
import com.sisnat.util.pagination.ResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@CrossOrigin({"*"})
@RequiredArgsConstructor
@RequestMapping("/nomeCientifico")
public class NomeCientificoController {

    private final NomeCientificoApp app;

    @PostMapping
    public ResponseEntity<NomeCientificoDTO> salvar(@RequestBody final NomeCientificoInputDTO dto) {
        log.info("c=NomeCientificoController, m=salvar, dto={}", dto);

        final var resultado = app.salvarNomeCientifico(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<NomeCientificoDTO> atualizar(@PathVariable Long id,  @RequestBody final NomeCientificoInputDTO dto) {
        log.info("c=NomeCientificoController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarNomeCientifico(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<NomeCientificoDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=NomeCientificoController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<NomeCientificoDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=NomeCientificoController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
    public ResponseEntity<ResponseList<NomeCientificoDTO>> buscarTodosNomeCientifico(NomeCientificoFindDTO dto) {
        log.info("c=NomeCientificoController, m=buscarTodosNomeCientifico, dto={}", dto);

        final var resultado = app.buscarTodosNomeCientifico(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
