package com.shinysyntax.aida.aida.mapper;

import com.shinysyntax.aida.aida.dto.request.AgendaRequest;
import com.shinysyntax.aida.aida.dto.response.AgendaResponse;
import com.shinysyntax.aida.aida.entity.Agenda;
import com.shinysyntax.aida.aida.entity.Colaborador;

public class AgendaMapper {

    public static Agenda toEntity(AgendaRequest req, Colaborador colaborador) {
        if (req == null) return null;
        Agenda a = new Agenda();
        a.setId(req.getId());
        a.setTipo(req.getTipo());
        a.setDescricao(req.getDescricao());
        a.setDataHora(req.getDataHora());
        a.setPrioridade(req.getPrioridade());
        a.setPlataforma(req.getPlataforma());
        a.setStatus(req.getStatus());
        a.setColaborador(colaborador);
        return a;
    }

    public static AgendaResponse toResponse(Agenda a) {
        if (a == null) return null;
        AgendaResponse r = new AgendaResponse();
        r.setId(a.getId());
        r.setTipo(a.getTipo());
        r.setDescricao(a.getDescricao());
        r.setDataHora(a.getDataHora());
        r.setPrioridade(a.getPrioridade());
        r.setPlataforma(a.getPlataforma());
        r.setStatus(a.getStatus());
        if (a.getColaborador() != null) r.setColaboradorCpf(a.getColaborador().getCpf());
        return r;
    }
}
