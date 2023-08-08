package com.sisnat.api.controller;

import com.sisnat.api.app.statusAnimal.StatusAnimalApp;
import com.sisnat.api.app.statusAnimal.dto.StatusAnimalDTO;
import com.sisnat.api.app.statusAnimal.dto.StatusAnimalFindDTO;
import com.sisnat.api.app.statusAnimal.dto.StatusAnimalInputDTO;
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
@RequestMapping("/statusAnimal")
public class StatusAnimalController {

    private final StatusAnimalApp app;

    @PostMapping
    public ResponseEntity<StatusAnimalDTO> salvar(@RequestBody final StatusAnimalInputDTO dto) {
        log.info("c=StatusAnimalController, m=salvar, dto={}", dto);

        final var resultado = app.salvarStatusAnimal(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StatusAnimalDTO> atualizar(@PathVariable Long id,  @RequestBody final StatusAnimalInputDTO dto) {
        log.info("c=StatusAnimalController, m=atualizar, id={}, dto={}", id, dto);

        final var resultado = app.atualizarStatusAnimal(id, dto);

        return new ResponseEntity<>(resultado, OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<StatusAnimalDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=StatusAnimalController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StatusAnimalDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=StatusAnimalController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @GetMapping
    public ResponseEntity<ResponseList<StatusAnimalDTO>> buscarTodosStatusAnimal(StatusAnimalFindDTO dto) {
        log.info("c=StatusAnimalController, m=buscarTodosStatusAnimal, dto={}", dto);

        final var resultado = app.buscarTodosStatusAnimal(dto);

        return new ResponseEntity<>(resultado, OK);
    }
}
