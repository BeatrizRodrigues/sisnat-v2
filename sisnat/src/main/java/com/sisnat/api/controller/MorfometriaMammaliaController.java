package com.sisnat.api.controller;

import com.sisnat.api.app.morfometriaMammalia.MorfometriaMammaliaApp;
import com.sisnat.api.app.morfometriaMammalia.dto.MorfMammaliaDTO;
import com.sisnat.api.app.morfometriaMammalia.dto.MorfMammaliaFindDTO;
import com.sisnat.api.app.morfometriaMammalia.dto.MorfMammaliaInputDTO;
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
@RequestMapping("/morfMammalia")
//@Api(value = "[/morfMammalia] - API MorfometriaMammalia", tags = {"MorfometriaMammalia"})
public class MorfometriaMammaliaController {

    private final MorfometriaMammaliaApp app;

    @PostMapping
//    @ApiOperation(value = "Salvar MorfometriaMammalia", response = MorfMammaliaDTO.class, responseContainer = "MorfMammaliaDTO")
    public ResponseEntity<MorfMammaliaDTO> salvar(@RequestBody final MorfMammaliaInputDTO dto) {
        log.info("c=MorfometriaMammaliaController, m=salvar, dto={}", dto);

        final var resultado = app.salvarMorfometriaMammalia(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
//    @ApiOperation(value = "Atualizar MorfometriaMammalia", response = MorfMammaliaDTO.class, responseContainer = "MorfMammaliaDTO")
    public ResponseEntity<MorfMammaliaDTO> atualizar(@PathVariable Long id,  @RequestBody final MorfMammaliaInputDTO dto) {
        log.info("c=MorfometriaMammaliaController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarMorfometriaMammalia(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
//    @ApiOperation(value = "Buscar por ID", response = MorfMammaliaDTO.class, responseContainer = "MorfMammaliaDTO")
    public ResponseEntity<MorfMammaliaDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=MorfometriaMammaliaController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
//    @ApiOperation(value = "Deletar por ID", response = MorfMammaliaDTO.class, responseContainer = "MorfMammaliaDTO")
    public ResponseEntity<MorfMammaliaDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=MorfometriaMammaliaController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
//    @ApiOperation(value = "Buscar Todos os MorfometriaMammalia", response = ResponseList.class, responseContainer = "ResponseList")
    public ResponseEntity<ResponseList<MorfMammaliaDTO>> buscarTodasMorfometriaMammalia(MorfMammaliaFindDTO dto) {
        log.info("c=MorfometriaMammaliaController, m=buscarTodosMorfometriaMammalia, dto={}", dto);

        final var resultado = app.buscarTodosMorfometriaMammalia(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
