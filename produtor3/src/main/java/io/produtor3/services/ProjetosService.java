package io.produtor3.services;

import io.produtor3.dtos.ProjetoDTO;
import io.produtor3.entities.ProjetoEntity;
import io.produtor3.repositories.ProjetosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ProjetosService implements IProjetosService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProjetosRepository projetosRepository;

    @Override
    public ResponseEntity<?> cadastrar(ProjetoDTO projetoDTO) {
        return Optional.of(projetoDTO)
                .map(projDTO -> modelMapper.map(projDTO, ProjetoEntity.class))
                .map(projEntity -> projetosRepository.save(projEntity))
                .map(projEntity -> modelMapper.map(projEntity, ProjetoDTO.class))
                .map()
                .map()
                .orElseThrow();
    }
}
