package com.sisnat.api.controller;

import com.sisnat.api.app.atendimento.AtendimentoApp;
import com.sisnat.api.app.atendimento.dto.AtendimentoDTO;
import com.sisnat.api.app.atendimento.dto.AtendimentoFindDTO;
import com.sisnat.api.app.atendimento.dto.AtendimentoInputDTO;
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
@RequestMapping("/atendimento")
//@Api(value = "[/atendimento] - API Atendimento", tags = {"Atendimento"})
public class AtendimentoController {
    
    private final AtendimentoApp app;

    @PostMapping
//    @ApiOperation(value = "Salvar Atendimento", response = AtendimentoDTO.class, responseContainer = "AtendimentoDTO")
    public ResponseEntity<AtendimentoDTO> salvar(@RequestBody final AtendimentoInputDTO dto) {
        log.info("c=AtendimentoController, m=salvar, dto={}", dto);

        final var resultado = app.salvarAtendimento(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
//    @ApiOperation(value = "Atualizar Atendimento", response = AtendimentoDTO.class, responseContainer = "AtendimentoDTO")
    public ResponseEntity<AtendimentoDTO> atualizar(@PathVariable Long id,  @RequestBody final AtendimentoInputDTO dto) {
        log.info("c=AtendimentoController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarAtendimento(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
//    @ApiOperation(value = "Buscar por ID", response = AtendimentoDTO.class, responseContainer = "AtendimentoDTO")
    public ResponseEntity<AtendimentoDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=AtendimentoController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
//    @ApiOperation(value = "Deletar por ID", response = AtendimentoDTO.class, responseContainer = "AtendimentoDTO")
    public ResponseEntity<AtendimentoDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=AtendimentoController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
//    @ApiOperation(value = "Buscar Todos os Atendimento", response = ResponseList.class, responseContainer = "ResponseList")
    public ResponseEntity<ResponseList<AtendimentoDTO>> buscarTodosAtendimento(AtendimentoFindDTO dto) {
        log.info("c=AtendimentoController, m=buscarTodosAtendimento, dto={}", dto);

        final var resultado = app.buscarTodosAtendimento(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
