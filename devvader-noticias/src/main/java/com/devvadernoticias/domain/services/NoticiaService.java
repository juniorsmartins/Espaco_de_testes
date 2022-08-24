package com.devvadernoticias.domain.services;

import com.devvadernoticias.domain.dtos.request.NoticiaDtoIn;
import com.devvadernoticias.domain.dtos.response.NoticiaDtoOut;
import com.devvadernoticias.domain.entities.NoticiaEntity;
import com.devvadernoticias.domain.ports.INoticiaService;
import com.devvadernoticias.infra.INoticiaRepository;
import com.netflix.discovery.converters.Auto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public final class NoticiaService implements INoticiaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private INoticiaRepository iNoticiaRepository;

    @Override
    public ResponseEntity<?> criar(NoticiaDtoIn noticiaDtoIn) {

        final var noticiaDeSaida = Optional.of(noticiaDtoIn)
                .map(noticiaDeEntrada -> modelMapper.map(noticiaDeEntrada, NoticiaEntity.class))
                .map(noticiaEntityParaSalvar -> {
                    // verificar existência de cliente em outro microservices
                    return iNoticiaRepository.saveAndFlush(noticiaEntityParaSalvar);})
                .map(noticiaEntitySalva -> modelMapper.map(noticiaEntitySalva, NoticiaDtoOut.class))
                .orElseThrow();

        return ResponseEntity
                .created(URI.create("/".concat(String.valueOf(noticiaDeSaida.getNoticiaId()))))
                .body(noticiaDeSaida);
    }
}
