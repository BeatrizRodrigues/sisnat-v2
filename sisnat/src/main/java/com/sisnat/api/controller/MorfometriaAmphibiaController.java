package com.sisnat.api.controller;

import com.sisnat.api.app.morfometriaAmphibia.MorfometriaAmphibiaApp;
import com.sisnat.api.app.morfometriaAmphibia.dto.MorfAmphibiaDTO;
import com.sisnat.api.app.morfometriaAmphibia.dto.MorfAmphibiaFindDTO;
import com.sisnat.api.app.morfometriaAmphibia.dto.MorfAmphibiaInputDTO;
import com.sisnat.util.pagination.ResponseList;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/morfAmphibia")
//@Api(value = "[/morfAmphibia] - API MorfometriaAmphibia", tags = {"MorfometriaAmphibia"})
public class MorfometriaAmphibiaController {
    
    private final MorfometriaAmphibiaApp app;

    @PostMapping
//    @ApiOperation(value = "Salvar MorfometriaAmphibia", response = MorfAmphibiaDTO.class, responseContainer = "MorfAmphibiaDTO")
    public ResponseEntity<MorfAmphibiaDTO> salvar(@RequestBody final MorfAmphibiaInputDTO dto) {
        log.info("c=MorfometriaAmphibiaController, m=salvar, dto={}", dto);

        final var resultado = app.salvarMorfometriaAmphibia(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
//    @ApiOperation(value = "Atualizar MorfometriaAmphibia", response = MorfAmphibiaDTO.class, responseContainer = "MorfAmphibiaDTO")
    public ResponseEntity<MorfAmphibiaDTO> atualizar(@PathVariable Long id,  @RequestBody final MorfAmphibiaInputDTO dto) {
        log.info("c=MorfometriaAmphibiaController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarMorfometriaAmphibia(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
//    @ApiOperation(value = "Buscar por ID", response = MorfAmphibiaDTO.class, responseContainer = "MorfAmphibiaDTO")
    public ResponseEntity<MorfAmphibiaDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=MorfometriaAmphibiaController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
//    @ApiOperation(value = "Deletar por ID", response = MorfAmphibiaDTO.class, responseContainer = "MorfAmphibiaDTO")
    public ResponseEntity<MorfAmphibiaDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=MorfometriaAmphibiaController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
    @ApiOperation(value = "Buscar Todos os MorfometriaAmphibia", response = ResponseList.class, responseContainer = "ResponseList")
    public ResponseEntity<ResponseList<MorfAmphibiaDTO>> buscarTodosMorfometriaAmphibia(MorfAmphibiaFindDTO dto) {
        log.info("c=MorfometriaAmphibiaController, m=buscarTodosMorfometriaAmphibia, dto={}", dto);

        final var resultado = app.buscarTodosMorfometriaAmphibia(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
