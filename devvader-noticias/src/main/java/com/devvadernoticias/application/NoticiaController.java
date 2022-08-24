package com.devvadernoticias.application;

import com.devvadernoticias.domain.dtos.request.NoticiaDtoIn;
import com.devvadernoticias.domain.ports.INoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/noticias")
public class NoticiaController {

    @Autowired
    private INoticiaService iNoticiaService;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid NoticiaDtoIn noticiaDtoIn) {
        return iNoticiaService.criar(noticiaDtoIn);
    }
}
