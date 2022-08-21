package com.devvaderclientes.domain.services;

import com.devvaderclientes.domain.ports.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteService iClienteService;


}
