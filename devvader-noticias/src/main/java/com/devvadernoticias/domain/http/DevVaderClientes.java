package com.devvadernoticias.domain.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("devvader-clientes")
public interface DevVaderClientes {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/clientes/{id}")
    ResponseEntity<?> consultarClientePorId(@PathVariable Long id);
}
