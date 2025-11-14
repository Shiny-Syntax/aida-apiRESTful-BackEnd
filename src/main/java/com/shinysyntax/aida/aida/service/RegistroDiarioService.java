package com.shinysyntax.aida.aida.service;

import java.util.List;

import com.shinysyntax.aida.aida.entity.RegistroDiario;

public interface RegistroDiarioService {
    RegistroDiario create(RegistroDiario registro);
    RegistroDiario update(Long id, RegistroDiario registro);
    RegistroDiario findById(Long id);
    List<RegistroDiario> findAll();
    void delete(Long id);
}
