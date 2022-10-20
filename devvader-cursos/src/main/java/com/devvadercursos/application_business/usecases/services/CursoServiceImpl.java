package com.devvadercursos.application_business.usecases.services;

import com.devvadercursos.application_business.usecases.dtos.CursoAtualizarDTO;
import com.devvadercursos.application_business.usecases.dtos.CursoDTO;
import com.devvadercursos.application_business.usecases.dtos.FiltroBuscarTodos;
import com.devvadercursos.application_business.usecases.excecoes.InternalErrorsException;
import com.devvadercursos.application_business.usecases.excecoes.MensagemPadrao;
import com.devvadercursos.application_business.usecases.excecoes.RecursoNaoEncontradoException;
import com.devvadercursos.enterprise_business.entities.Curso;
import com.devvadercursos.frameworks_drivers.ICursosRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
@Slf4j
public class CursoServiceImpl implements IGenericsService<CursoDTO, FiltroBuscarTodos, CursoAtualizarDTO, Curso, Long> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ICursosRepository iCursosRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTO> cadastrar(CursoDTO dtoIn) {
        return Optional.of(dtoIn)
                .map(cursoDTO -> {
                    var curso = modelMapper.map(cursoDTO, Curso.class);
                    curso.setDataHoraCadastro(Instant.now());
                    iCursosRepository.saveAndFlush(curso);

                    cursoDTO = modelMapper.map(curso, CursoDTO.class);
                    converterInstantEmDataHoraLocal(curso, cursoDTO);

                    log.info(MensagemPadrao.CONCLUIDO_SUCESSO);
                    return ResponseEntity
                            .created(URI.create("/" + curso.getId()))
                            .body(cursoDTO);
                })
                .orElseThrow(() -> {
                    log.error(MensagemPadrao.ERRO_INTERNO);
                    return new InternalErrorsException(MensagemPadrao.ERRO_INTERNO);
                });
    }

        private void converterInstantEmDataHoraLocal(Curso curso, CursoDTO cursoDTO) {
            cursoDTO.setDataHoraCadastro(LocalDateTime.ofInstant(curso.getDataHoraCadastro(), ZoneId.systemDefault()));
            if(curso.getDataHoraUltimaAtualizacao() != null)
                cursoDTO.setDataHoraUltimaAtualizacao(LocalDateTime.ofInstant(curso.getDataHoraUltimaAtualizacao(),
                        ZoneId.systemDefault()));
        }

    @Override
    public ResponseEntity<Page<CursoDTO>> buscarTodos(Pageable paginacao, FiltroBuscarTodos filtro) {
        return ResponseEntity
                .ok()
                .body(iCursosRepository.findAll(configurarFiltro(filtro), paginacao)
                            .map(curso -> {
                                var cursoDTO = modelMapper.map(curso, CursoDTO.class);
                                converterInstantEmDataHoraLocal(curso, cursoDTO);

                                log.info(MensagemPadrao.CONCLUIDO_SUCESSO);
                                return cursoDTO;
                            }));
    }

        private Example<Curso> configurarFiltro(FiltroBuscarTodos filtro) {
            // ExampleMatcher - permite configurar condições para serem aplicadas nos filtros
            ExampleMatcher exampleMatcher = ExampleMatcher
                    .matchingAll()
                    .withIgnoreCase() // Ignorar caixa alta ou baixa - quando String
                    .withIgnoreNullValues() // Ignorar valores nulos
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // permite encontrar palavras parecidas - tipo Like do SQL
                    .withIgnorePaths("dataInicio"); // Ignorar atributo ou atributos específicos

            // Example - pega campos populados para criar filtros
            return Example.of(modelMapper.map(filtro, Curso.class), exampleMatcher);
        }

    @Override
    public ResponseEntity<CursoDTO> consultarPorId(Long id) {
        return iCursosRepository.findById(id)
                .map(curso -> {
                    var cursoDTO = modelMapper.map(curso, CursoDTO.class);
                    converterInstantEmDataHoraLocal(curso, cursoDTO);

                    log.info(MensagemPadrao.CONCLUIDO_SUCESSO);
                    return ResponseEntity
                            .ok()
                            .body(cursoDTO);
                })
                .orElseThrow(() -> {
                    log.warn(MensagemPadrao.ID_NAO_ENCONTRADO);
                    return new RecursoNaoEncontradoException(MensagemPadrao.ID_NAO_ENCONTRADO);
                });
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTO> atualizarTotalOuSalvar(Long id, CursoDTO dtoIn) {
        return Optional.of(dtoIn)
                .map(cursoDTO -> {
                    var cursoEntity = iCursosRepository.findById(id);
                    if(!cursoEntity.isPresent()) {
                        var curso = modelMapper.map(cursoDTO, Curso.class);
                        curso.setDataHoraCadastro(Instant.now());
                        iCursosRepository.saveAndFlush(curso);

                        cursoDTO = modelMapper.map(curso, CursoDTO.class);
                        converterInstantEmDataHoraLocal(curso, cursoDTO);
                    } else {
                        cursoEntity.get().setTitulo(cursoDTO.getTitulo());
                        cursoEntity.get().setDescricao(cursoDTO.getDescricao());
                        cursoEntity.get().setTematica(cursoDTO.getTematica());
                        cursoEntity.get().setDataInicio(cursoDTO.getDataInicio());
                        cursoEntity.get().setDataFim(cursoDTO.getDataFim());
                        cursoEntity.get().setCliente(cursoDTO.getCliente());
                        cursoEntity.get().setDataHoraUltimaAtualizacao(Instant.now());

                        cursoDTO = modelMapper.map(cursoEntity.get(), CursoDTO.class);
                        converterInstantEmDataHoraLocal(cursoEntity.get(), cursoDTO);
                    }

                    log.info(MensagemPadrao.CONCLUIDO_SUCESSO);
                    return ResponseEntity
                            .ok()
                            .body(cursoDTO);
                }).orElseThrow(() -> {
                    log.error(MensagemPadrao.ERRO_INTERNO);
                    return new InternalErrorsException(MensagemPadrao.ERRO_INTERNO);
                });
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<CursoDTO> atualizarParcialOuLancarExcecao(Long id, CursoAtualizarDTO dtoIn) {
        return iCursosRepository.findById(id)
                .map(curso -> {
                    curso.setTitulo(dtoIn.getTitulo());
                    curso.setDescricao(dtoIn.getDescricao());
                    curso.setTematica(dtoIn.getTematica());
                    curso.setDataInicio(dtoIn.getDataInicio());
                    curso.setDataFim(dtoIn.getDataFim());
                    curso.setDataHoraUltimaAtualizacao(Instant.now());

                    var cursoDTO = modelMapper.map(curso, CursoDTO.class);
                    converterInstantEmDataHoraLocal(curso, cursoDTO);

                    log.info(MensagemPadrao.CONCLUIDO_SUCESSO);
                    return ResponseEntity
                            .ok()
                            .body(cursoDTO);
                }).orElseThrow(() -> {
                    log.warn(MensagemPadrao.ID_NAO_ENCONTRADO);
                    return new RecursoNaoEncontradoException(MensagemPadrao.ID_NAO_ENCONTRADO);
                });
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<?> deletarPorId(Long id) {
        return iCursosRepository.findById(id)
                        .map(curso -> {
                            iCursosRepository.delete(curso);
                            return ResponseEntity
                                    .ok()
                                    .body(MensagemPadrao.RECURSO_DELETADO);
                        }).orElseThrow(() -> {
                            log.warn(MensagemPadrao.ID_NAO_ENCONTRADO);
                            return new RecursoNaoEncontradoException(MensagemPadrao.ID_NAO_ENCONTRADO);
                        });
    }
}
