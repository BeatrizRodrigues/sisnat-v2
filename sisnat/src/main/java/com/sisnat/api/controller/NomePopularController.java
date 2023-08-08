package com.sisnat.api.controller;

import com.sisnat.api.app.nomePopular.NomePopularApp;
import com.sisnat.api.app.nomePopular.dto.NomePopularDTO;
import com.sisnat.api.app.nomePopular.dto.NomePopularFindDTO;
import com.sisnat.api.app.nomePopular.dto.NomePopularInputDTO;
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
@RequestMapping("/nomePopular")
public class NomePopularController {

    private final NomePopularApp app;

    @PostMapping
    public ResponseEntity<NomePopularDTO> salvar(@RequestBody final NomePopularInputDTO dto) {
        log.info("c=NomePopularController, m=salvar, dto={}", dto);

        final var resultado = app.salvarNomePopular(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<NomePopularDTO> atualizar(@PathVariable Long id,  @RequestBody final NomePopularInputDTO dto) {
        log.info("c=NomePopularController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarNomePopular(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<NomePopularDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=NomePopularController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<NomePopularDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=NomePopularController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
    public ResponseEntity<ResponseList<NomePopularDTO>> buscarTodosNomePopular(NomePopularFindDTO dto) {
        log.info("c=NomePopularController, m=buscarTodosNomePopular, dto={}", dto);

        final var resultado = app.buscarTodosNomePopular(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
