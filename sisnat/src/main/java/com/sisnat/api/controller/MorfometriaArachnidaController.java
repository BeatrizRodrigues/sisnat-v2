package com.sisnat.api.controller;

import com.sisnat.api.app.morfometriaArachnida.MorfometriaArachnidaApp;
import com.sisnat.api.app.morfometriaArachnida.dto.MorfArachnidaDTO;
import com.sisnat.api.app.morfometriaArachnida.dto.MorfArachnidaFindDTO;
import com.sisnat.api.app.morfometriaArachnida.dto.MorfArachnidaInputDTO;
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
@RequestMapping("/morfArachnida")
//@Api(value = "[/morfArachnida] - API MorfometriaArachnida", tags = {"MorfometriaArachnida"})
public class MorfometriaArachnidaController {

    private final MorfometriaArachnidaApp app;

    @PostMapping
//    @ApiOperation(value = "Salvar MorfometriaArachnida", response = MorfArachnidaDTO.class, responseContainer = "MorfArachnidaDTO")
    public ResponseEntity<MorfArachnidaDTO> salvar(@RequestBody final MorfArachnidaInputDTO dto) {
        log.info("c=MorfometriaArachnidaController, m=salvar, dto={}", dto);

        final var resultado = app.salvarMorfometriaArachnida(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
//    @ApiOperation(value = "Atualizar MorfometriaArachnida", response = MorfArachnidaDTO.class, responseContainer = "MorfArachnidaDTO")
    public ResponseEntity<MorfArachnidaDTO> atualizar(@PathVariable Long id,  @RequestBody final MorfArachnidaInputDTO dto) {
        log.info("c=MorfometriaArachnidaController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarMorfometriaArachnida(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
//    @ApiOperation(value = "Buscar por ID", response = MorfArachnidaDTO.class, responseContainer = "MorfArachnidaDTO")
    public ResponseEntity<MorfArachnidaDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=MorfometriaArachnidaController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
//    @ApiOperation(value = "Deletar por ID", response = MorfArachnidaDTO.class, responseContainer = "MorfArachnidaDTO")
    public ResponseEntity<MorfArachnidaDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=MorfometriaArachnidaController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
//    @ApiOperation(value = "Buscar Todos os MorfometriaArachnida", response = ResponseList.class, responseContainer = "ResponseList")
    public ResponseEntity<ResponseList<MorfArachnidaDTO>> buscarTodosMorfometriaArachnida(MorfArachnidaFindDTO dto) {
        log.info("c=MorfometriaArachnidaController, m=buscarTodosMorfometriaArachnida, dto={}", dto);

        final var resultado = app.buscarTodosMorfometriaArachnida(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
