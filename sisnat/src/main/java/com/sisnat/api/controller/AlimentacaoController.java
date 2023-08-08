package com.sisnat.api.controller;

import com.sisnat.api.app.alimentacao.AlimentacaoApp;
import com.sisnat.api.app.alimentacao.dto.AlimentacaoDTO;
import com.sisnat.api.app.alimentacao.dto.AlimentacaoFindDTO;
import com.sisnat.api.app.alimentacao.dto.AlimentacaoInputDTO;
import com.sisnat.util.pagination.ResponseList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin({"*"})
@RequestMapping("/alimentacao")
@Api(value = "[/alimentacao] - API Alimentacao", tags = {"Alimentacao"})
public class AlimentacaoController {

    private final AlimentacaoApp app;

    @PostMapping
//    @ApiOperation(value = "Salvar Alimentacao", response = AlimentacaoDTO.class, responseContainer = "AlimentacaoDTO")
    public ResponseEntity<AlimentacaoDTO> salvar(@RequestBody final AlimentacaoInputDTO dto) {
        log.info("c=AlimentacaoController, m=salvar, dto={}", dto);

        final var resultado = app.salvarAlimentacao(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
//    @ApiOperation(value = "Atualizar Alimentacao", response = AlimentacaoDTO.class, responseContainer = "AlimentacaoDTO")
    public ResponseEntity<AlimentacaoDTO> atualizar(@PathVariable Long id,  @RequestBody final AlimentacaoInputDTO dto) {
        log.info("c=AlimentacaoController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarAlimentacao(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
//    @ApiOperation(value = "Buscar por ID", response = AlimentacaoDTO.class, responseContainer = "AlimentacaoDTO")
    public ResponseEntity<AlimentacaoDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=AlimentacaoController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
//    @ApiOperation(value = "Deletar por ID", response = AlimentacaoDTO.class, responseContainer = "AlimentacaoDTO")
    public ResponseEntity<AlimentacaoDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=AlimentacaoController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
    @ApiOperation(value = "Buscar Todos os Alimentacao", response = ResponseList.class, responseContainer = "ResponseList")
    public ResponseEntity<ResponseList<AlimentacaoDTO>> buscarTodosAlimentacao(AlimentacaoFindDTO dto) {
        log.info("c=AlimentacaoController, m=buscarTodosAlimentacao, dto={}", dto);

        final var resultado = app.buscarTodosAlimentacao(dto);

        return new ResponseEntity<>(resultado, OK);
    }

}
