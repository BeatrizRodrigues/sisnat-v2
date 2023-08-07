package com.sisnat.api.controller;

import com.sisnat.api.app.ecdise.EcdiseApp;
import com.sisnat.api.app.ecdise.dto.EcdiseDTO;
import com.sisnat.api.app.ecdise.dto.EcdiseFindDTO;
import com.sisnat.api.app.ecdise.dto.EcdiseInputDTO;
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
@RequestMapping("/ecdise")
//@Api(value = "[/ecdise] - API Ecdise", tags = {"Ecdise"})
public class EcdiseController {
    
    private final EcdiseApp app;

    @PostMapping
//    @ApiOperation(value = "Salvar Ecdise", response = EcdiseDTO.class, responseContainer = "EcdiseDTO")
    public ResponseEntity<EcdiseDTO> salvar(@RequestBody final EcdiseInputDTO dto) {
        log.info("c=EcdiseController, m=salvar, dto={}", dto);

        final var resultado = app.salvarEcdise(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
//    @ApiOperation(value = "Atualizar Ecdise", response = EcdiseDTO.class, responseContainer = "EcdiseDTO")
    public ResponseEntity<EcdiseDTO> atualizar(@PathVariable Long id,  @RequestBody final EcdiseInputDTO dto) {
        log.info("c=EcdiseController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarEcdise(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
//    @ApiOperation(value = "Buscar por ID", response = EcdiseDTO.class, responseContainer = "EcdiseDTO")
    public ResponseEntity<EcdiseDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=EcdiseController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
//    @ApiOperation(value = "Deletar por ID", response = EcdiseDTO.class, responseContainer = "EcdiseDTO")
    public ResponseEntity<EcdiseDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=EcdiseController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
//    @ApiOperation(value = "Buscar Todos os Ecdise", response = ResponseList.class, responseContainer = "ResponseList")
    public ResponseEntity<ResponseList<EcdiseDTO>> buscarTodosEcdise(EcdiseFindDTO dto) {
        log.info("c=EcdiseController, m=buscarTodosEcdise, dto={}", dto);

        final var resultado = app.buscarTodosEcdise(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
