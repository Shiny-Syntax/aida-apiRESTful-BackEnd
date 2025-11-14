package com.shinysyntax.aida.aida.service;

import java.util.List;

import com.shinysyntax.aida.aida.entity.Agenda;

public interface AgendaService {
    Agenda create(Agenda agenda);
    Agenda update(Long id, Agenda agenda);
    Agenda findById(Long id);
    List<Agenda> findAll();
    void delete(Long id);
}
