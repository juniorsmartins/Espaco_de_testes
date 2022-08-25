package com.devvaderclientes.domain.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("devvader-noticias")
public interface IDevVaderNoticias {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/noticias/clientes/{id}")
    List<NoticiaDtoOut> buscarNoticiasPorIdDoCliente(@PathVariable Long id);
}
