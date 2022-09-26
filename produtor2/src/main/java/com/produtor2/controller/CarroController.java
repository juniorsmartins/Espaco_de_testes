package com.produtor2.controller;

import com.produtor2.dto.CarroDTO;
import com.produtor2.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody CarroDTO carroDTO) {
        return carroService.cadastrar(carroDTO);
    }
}
