package com.sisnat.api.controller;

import com.sisnat.api.app.morfometriaReptilia.MorfometriaReptiliaApp;
import com.sisnat.api.app.morfometriaReptilia.dto.MorfReptiliaDTO;
import com.sisnat.api.app.morfometriaReptilia.dto.MorfReptiliaFindDTO;
import com.sisnat.api.app.morfometriaReptilia.dto.MorfReptiliaInputDTO;
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
@RequestMapping("/morfReptilia")
public class MorfometriaReptiliaController {


    private final MorfometriaReptiliaApp app;

    @PostMapping
    public ResponseEntity<MorfReptiliaDTO> salvar(@RequestBody final MorfReptiliaInputDTO dto) {
        log.info("c=MorfometriaReptiliaController, m=salvar, dto={}", dto);

        final var resultado = app.salvarMorfometriaReptilia(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MorfReptiliaDTO> atualizar(@PathVariable Long id,  @RequestBody final MorfReptiliaInputDTO dto) {
        log.info("c=MorfometriaReptiliaController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarMorfometriaReptilia(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<MorfReptiliaDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=MorfometriaReptiliaController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
   public ResponseEntity<MorfReptiliaDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=MorfometriaReptiliaController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
    public ResponseEntity<ResponseList<MorfReptiliaDTO>> buscarTodasMorfometriaReptilia(MorfReptiliaFindDTO dto) {
        log.info("c=MorfometriaReptiliaController, m=buscarTodosMorfometriaReptilia, dto={}", dto);

        final var resultado = app.buscarTodosMorfometriaReptilia(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
