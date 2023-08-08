package com.sisnat.api.controller;


import com.sisnat.api.app.motivo.MotivoApp;
import com.sisnat.api.app.motivo.dto.MotivoDTO;
import com.sisnat.api.app.motivo.dto.MotivoFindDTO;
import com.sisnat.api.app.motivo.dto.MotivoInputDTO;
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
@RequestMapping("/motivo")
public class MotivoController {

    private final MotivoApp app;

    @PostMapping
//    @ApiOperation(value = "Salvar Motivo", response = MotivoDTO.class, responseContainer = "MotivoDTO")
    public ResponseEntity<MotivoDTO> salvar(@RequestBody final MotivoInputDTO dto) {
        log.info("c=MotivoController, m=salvar, dto={}", dto);

        final var resultado = app.salvarMotivo(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
//    @ApiOperation(value = "Atualizar Motivo", response = MotivoDTO.class, responseContainer = "MotivoDTO")
    public ResponseEntity<MotivoDTO> atualizar(@PathVariable Long id,  @RequestBody final MotivoInputDTO dto) {
        log.info("c=MotivoController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarMotivo(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
//    @ApiOperation(value = "Buscar por ID", response = MotivoDTO.class, responseContainer = "MotivoDTO")
    public ResponseEntity<MotivoDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=MotivoController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
//    @ApiOperation(value = "Deletar por ID", response = MotivoDTO.class, responseContainer = "MotivoDTO")
    public ResponseEntity<MotivoDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=MotivoController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
//    @ApiOperation(value = "Buscar Todos os Motivo", response = ResponseList.class, responseContainer = "ResponseList")
    public ResponseEntity<ResponseList<MotivoDTO>> buscarTodosMotivo(MotivoFindDTO dto) {
        log.info("c=MotivoController, m=buscarTodosMotivo, dto={}", dto);

        final var resultado = app.buscarTodosMotivo(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
