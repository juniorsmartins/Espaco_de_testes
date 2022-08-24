package com.devvadernoticias.domain.ports;

import com.devvadernoticias.domain.dtos.request.NoticiaDtoIn;
import org.springframework.http.ResponseEntity;

public interface INoticiaService {

    ResponseEntity<?> criar(NoticiaDtoIn noticiaDtoIn);
}
