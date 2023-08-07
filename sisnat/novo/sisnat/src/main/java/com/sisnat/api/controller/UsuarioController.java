package com.sisnat.api.controller;

import com.sisnat.api.app.usuario.UsuarioApp;
import com.sisnat.api.app.usuario.dto.UsuarioDTO;
import com.sisnat.api.app.usuario.dto.UsuarioInputDTO;
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
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioApp app;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody final UsuarioInputDTO dto) {
        log.info("c=UsuarioController, m=salvar, dto={}", dto);

        final var resultado = app.salvarUsuario(dto);

        return new ResponseEntity<>(resultado, CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorID(@PathVariable Long id) {
        log.info("c=UsuarioController, m=buscarPorID, id={}", id);

        final var resultado = app.buscarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> deletarPorID(@PathVariable Long id) {
        log.info("c=UsuarioController, m=deletarPorID, id={}", id);

        final var resultado = app.deletarPorID(id);

        return new ResponseEntity<>(resultado, OK);
    }
}
