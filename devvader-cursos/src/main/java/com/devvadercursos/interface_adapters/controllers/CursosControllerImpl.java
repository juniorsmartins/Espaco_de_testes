package com.devvadercursos.interface_adapters.controllers;

import com.devvadercursos.application_business.usecases.dtos.CursoDTO;
import com.devvadercursos.application_business.usecases.dtos.FiltroBuscarTodos;
import com.devvadercursos.application_business.usecases.excecoes.MensagemPadrao;
import com.devvadercursos.application_business.usecases.services.IGenericsService;
import com.devvadercursos.enterprise_business.entities.Curso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/cursos")
public final class CursosControllerImpl extends CursosController<CursoDTO, FiltroBuscarTodos, Long> {

    private static Logger logger = LoggerFactory.getLogger(CursosControllerImpl.class);

    @Autowired
    private IGenericsService<CursoDTO, FiltroBuscarTodos, Curso, Long> cursoService;

    @Override
    public ResponseEntity<CursoDTO> cadastrar(@RequestBody @Valid CursoDTO dtoIn) {
        logger.info(MensagemPadrao.PROCEDIMENTO_CADASTRAR);
        return cursoService.cadastrar(dtoIn);
    }

    @Override
    public ResponseEntity<Page<CursoDTO>> buscarTodos(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0,
            size = 3) Pageable paginacao, FiltroBuscarTodos filtro) {
        logger.info(MensagemPadrao.PROCEDIMENTO_BUSCAR_TODOS);
        return cursoService.buscarTodos(paginacao, filtro);
    }

    @Override
    public ResponseEntity<CursoDTO> consultarPorId(@PathVariable(value = "id") Long id) {
        logger.info(MensagemPadrao.PROCEDIMENTO_CONSULTAR_POR_ID);
        return cursoService.consultarPorId(id);
    }

    @Override
    public String consultarPorta(@Value("${local.server.port}") String porta) {
        return String.format("Porta: %s", porta);
    }

    @Override
    public ResponseEntity<CursoDTO> atualizarTotalOuSalvar(@PathVariable(value = "id") Long id, @RequestBody @Valid CursoDTO cursoDTO) {
        logger.info(MensagemPadrao.PROCEDIMENTO_ATUALIZAR_TOTAL_OU_SALVAR);
        return cursoService.atualizarTotalOuSalvar(id, cursoDTO);
    }

    @Override
    public ResponseEntity<CursoDTO> atualizarParcialOuLancarExcecao(@PathVariable(value = "id") Long id, @RequestBody
        @Valid CursoDTO cursoDTO) {
        logger.info(MensagemPadrao.PROCEDIMENTO_ATUALIZAR_PARCIAL_OU_EXCEPTION);
        return cursoService.atualizarParcialOuLancarExcecao(id, cursoDTO);
    }

    @Override
    public ResponseEntity<?> deletarPorId(@PathVariable(value = "id") Long id) {
        logger.info(MensagemPadrao.PROCEDIMENTO_DELETAR_POR_ID);
        return cursoService.deletarPorId(id);
    }
}
