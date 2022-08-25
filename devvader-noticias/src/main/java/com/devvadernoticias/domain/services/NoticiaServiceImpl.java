package com.devvadernoticias.domain.services;

import com.devvadernoticias.domain.dtos.request.NoticiaDtoIn;
import com.devvadernoticias.domain.dtos.response.NoticiaDtoOut;
import com.devvadernoticias.domain.entities.NoticiaEntity;
import com.devvadernoticias.domain.http.DevVaderClientes;
import com.devvadernoticias.domain.ports.INoticiaService;
import com.devvadernoticias.infra.INoticiaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public final class NoticiaServiceImpl implements INoticiaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private INoticiaRepository iNoticiaRepository;

    @Autowired
    private DevVaderClientes devVaderClientes;

    @Override
    public ResponseEntity<?> criar(NoticiaDtoIn noticiaDtoIn) {

        final var noticiaDeSaida = Optional.of(noticiaDtoIn)
                .map(noticiaDeEntrada -> modelMapper.map(noticiaDeEntrada, NoticiaEntity.class))
                .map(noticiaEntityParaSalvar -> {

                    Optional.of(devVaderClientes.consultarClientePorId(noticiaEntityParaSalvar.getCliente())
                            .getBody())
                            .orElseThrow();

                    return iNoticiaRepository.saveAndFlush(noticiaEntityParaSalvar);})
                .map(noticiaEntitySalva -> modelMapper.map(noticiaEntitySalva, NoticiaDtoOut.class))
                .orElseThrow();

        return ResponseEntity
                .created(URI.create("/".concat(String.valueOf(noticiaDeSaida.getNoticiaId()))))
                .body(noticiaDeSaida);
    }

    @Override
    public List<NoticiaDtoOut> buscarNoticiasPorIdDoCliente(Long id) {
        return iNoticiaRepository.findAllByCliente(id)
                .stream()
                .map(noticiaEntity -> modelMapper.map(noticiaEntity, NoticiaDtoOut.class))
                .toList();
    }


}
