package com.sisnat.api.controller;

import com.sisnat.api.app.morfometriaAve.MorfometriaAveApp;
import com.sisnat.api.app.morfometriaAve.dto.MorfAveDTO;
import com.sisnat.api.app.morfometriaAve.dto.MorfAveFindDTO;
import com.sisnat.api.app.morfometriaAve.dto.MorfAveInputDTO;
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
@RequestMapping("/morfAve")
//@Api(value = "[/morfReptilia] - API MorfometriaReptilia", tags = {"MorfometriaReptilia"})
public class MorfometriaAveController {

    private final MorfometriaAveApp app;

    @PostMapping
//    @ApiOperation(value = "Salvar MorfometriaAve", response = MorfAveDTO.class, responseContainer = "MorfAveDTO")
    public ResponseEntity<MorfAveDTO> salvar(@RequestBody final MorfAveInputDTO dto) {
        log.info("c=MorfometriaAveController, m=salvar, dto={}", dto);

        final var resultado = app.salvarMorfometriaAve(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
//    @ApiOperation(value = "Atualizar MorfometriaAve", response = MorfAveDTO.class, responseContainer = "MorfAveDTO")
    public ResponseEntity<MorfAveDTO> atualizar(@PathVariable Long id,  @RequestBody final MorfAveInputDTO dto) {
        log.info("c=MorfometriaAveController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarMorfometriaAve(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
//    @ApiOperation(value = "Buscar por ID", response = MorfAveDTO.class, responseContainer = "MorfAveDTO")
    public ResponseEntity<MorfAveDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=MorfometriaAveController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
//    @ApiOperation(value = "Deletar por ID", response = MorfAveDTO.class, responseContainer = "MorfAveDTO")
    public ResponseEntity<MorfAveDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=MorfometriaAveController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
//    @ApiOperation(value = "Buscar Todos os MorfometriaAve", response = ResponseList.class, responseContainer = "ResponseList")
    public ResponseEntity<ResponseList<MorfAveDTO>> buscarTodasMorfometriaAve(MorfAveFindDTO dto) {
        log.info("c=MorfometriaAveController, m=buscarTodosMorfometriaAve, dto={}", dto);

        final var resultado = app.buscarTodosMorfometriaAves(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
