package io.produtor3.controllers;

import io.produtor3.dtos.ProjetoDTO;
import io.produtor3.services.IProjetosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/projetos")
public class ProjetosController {

    @Autowired
    private IProjetosService iProjetosService;

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProjetoDTO projetoDTO) {
        return iProjetosService.cadastrar(projetoDTO);
    }
}
