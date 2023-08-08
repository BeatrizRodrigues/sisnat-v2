package com.sisnat.api.controller;

import com.sisnat.api.app.animal.AnimalApp;
import com.sisnat.api.app.animal.dto.AnimalDTO;
import com.sisnat.api.app.animal.dto.AnimalFindDTO;
import com.sisnat.api.app.animal.dto.AnimalInputDTO;
import com.sisnat.util.pagination.ResponseList;
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
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalApp app;

    @PostMapping
    public ResponseEntity<AnimalDTO> salvar(@RequestBody final AnimalInputDTO dto) {
        log.info("c=AnimalController, m=salvar, dto={}", dto);

        final var resultado = app.salvarAnimal(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
//    @ApiOperation(value = "Atualizar Animal", response = AnimalDTO.class, responseContainer = "AnimalDTO")
    public ResponseEntity<AnimalDTO> atualizar(@PathVariable Long id,  @RequestBody final AnimalInputDTO dto) {
        log.info("c=AnimalController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarAnimal(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
//    @ApiOperation(value = "Buscar por ID", response = AnimalDTO.class, responseContainer = "AnimalDTO")
    public ResponseEntity<AnimalDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=AnimalController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
//    @ApiOperation(value = "Deletar por ID", response = AnimalDTO.class, responseContainer = "AnimalDTO")
    public ResponseEntity<AnimalDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=AnimalController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
    @ApiOperation(value = "Buscar Todos os Animal", response = ResponseList.class, responseContainer = "ResponseList")
    public ResponseEntity<ResponseList<AnimalDTO>> buscarTodosAnimais(AnimalFindDTO dto) {
        log.info("c=AnimalController, m=buscarTodosAnimais, dto={}", dto);

        final var resultado = app.buscarTodosAnimais(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
