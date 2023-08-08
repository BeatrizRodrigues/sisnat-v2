package com.sisnat.api.controller;

import com.sisnat.api.app.diario.DiarioApp;
import com.sisnat.api.app.diario.dto.DiarioDTO;
import com.sisnat.api.app.diario.dto.DiarioFindDTO;
import com.sisnat.api.app.diario.dto.DiarioInputDTO;
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
@RequestMapping("/diario")
//@Api(value = "[/diario] - API Diario", tags = {"Diario"})
public class DiarioController {
    
    private final DiarioApp app;

    @PostMapping
//    @ApiOperation(value = "Salvar Diario", response = DiarioDTO.class, responseContainer = "DiarioDTO")
    public ResponseEntity<DiarioDTO> salvar(@RequestBody final DiarioInputDTO dto) {
        log.info("c=DiarioController, m=salvar, dto={}", dto);

        final var resultado = app.salvarDiario(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
//    @ApiOperation(value = "Atualizar Diario", response = DiarioDTO.class, responseContainer = "DiarioDTO")
    public ResponseEntity<DiarioDTO> atualizar(@PathVariable Long id,  @RequestBody final DiarioInputDTO dto) {
        log.info("c=DiarioController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarDiario(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
//    @ApiOperation(value = "Buscar por ID", response = DiarioDTO.class, responseContainer = "DiarioDTO")
    public ResponseEntity<DiarioDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=DiarioController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
//    @ApiOperation(value = "Deletar por ID", response = DiarioDTO.class, responseContainer = "DiarioDTO")
    public ResponseEntity<DiarioDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=DiarioController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
//    @ApiOperation(value = "Buscar Todos os Diario", response = ResponseList.class, responseContainer = "ResponseList")
    public ResponseEntity<ResponseList<DiarioDTO>> buscarTodosDiario(DiarioFindDTO dto) {
        log.info("c=DiarioController, m=buscarTodosDiario, dto={}", dto);

        final var resultado = app.buscarTodosDiario(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
