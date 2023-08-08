package com.sisnat.api.controller;


import com.sisnat.api.app.procedencia.ProcedenciaApp;
import com.sisnat.api.app.procedencia.dto.ProcedenciaDTO;
import com.sisnat.api.app.procedencia.dto.ProcedenciaFindDTO;
import com.sisnat.api.app.procedencia.dto.ProcedenciaInputDTO;
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
@RequestMapping("/procedencia")
public class ProcedenciaController {

    private final ProcedenciaApp app;

    @PostMapping
//    @ApiOperation(value = "Salvar Procedencia", response = ProcedenciaDTO.class, responseContainer = "ProcedenciaDTO")
    public ResponseEntity<ProcedenciaDTO> salvar(@RequestBody final ProcedenciaInputDTO dto) {
        log.info("c=ProcedenciaController, m=salvar, dto={}", dto);

        final var resultado = app.salvarProcedencia(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
//    @ApiOperation(value = "Atualizar Procedencia", response = ProcedenciaDTO.class, responseContainer = "ProcedenciaDTO")
    public ResponseEntity<ProcedenciaDTO> atualizar(@PathVariable Long id,  @RequestBody final ProcedenciaInputDTO dto) {
        log.info("c=ProcedenciaController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarProcedencia(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
//    @ApiOperation(value = "Buscar por ID", response = ProcedenciaDTO.class, responseContainer = "ProcedenciaDTO")
    public ResponseEntity<ProcedenciaDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=ProcedenciaController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
//    @ApiOperation(value = "Deletar por ID", response = ProcedenciaDTO.class, responseContainer = "ProcedenciaDTO")
    public ResponseEntity<ProcedenciaDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=ProcedenciaController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
//    @ApiOperation(value = "Buscar Todos os Procedencia", response = ResponseList.class, responseContainer = "ResponseList")
    public ResponseEntity<ResponseList<ProcedenciaDTO>> buscarTodosProcedencia(ProcedenciaFindDTO dto) {
        log.info("c=ProcedenciaController, m=buscarTodosProcedencia, dto={}", dto);

        final var resultado = app.buscarTodosProcedencia(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
