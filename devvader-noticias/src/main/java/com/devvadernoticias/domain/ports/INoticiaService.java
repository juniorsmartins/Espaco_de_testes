package com.devvadernoticias.domain.ports;

import com.devvadernoticias.domain.dtos.request.NoticiaDtoIn;
import com.devvadernoticias.domain.dtos.response.NoticiaDtoOut;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INoticiaService {

    ResponseEntity<?> criar(NoticiaDtoIn noticiaDtoIn);
    List<NoticiaDtoOut> buscarNoticiasPorIdDoCliente(Long id);
}
