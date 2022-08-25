package com.devvadernoticias.application;

import com.devvadernoticias.domain.dtos.request.NoticiaDtoIn;
import com.devvadernoticias.domain.dtos.response.NoticiaDtoOut;
import com.devvadernoticias.domain.ports.INoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/noticias")
public class NoticiaController {

    @Autowired
    private INoticiaService iNoticiaService;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid NoticiaDtoIn noticiaDtoIn) {
        return iNoticiaService.criar(noticiaDtoIn);
    }

    @GetMapping(value = "/clientes/{id}")
    List<NoticiaDtoOut> buscarNoticiasPorIdDoCliente(@PathVariable Long id) {
        return iNoticiaService.buscarNoticiasPorIdDoCliente(id);
    }
}
