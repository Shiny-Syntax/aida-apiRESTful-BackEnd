package com.shinysyntax.aida.aida.mapper;

import com.shinysyntax.aida.aida.dto.request.RegistroDiarioRequest;
import com.shinysyntax.aida.aida.dto.response.RegistroDiarioResponse;
import com.shinysyntax.aida.aida.entity.Colaborador;
import com.shinysyntax.aida.aida.entity.RegistroDiario;

public class RegistroDiarioMapper {

    public static RegistroDiario toEntity(RegistroDiarioRequest req, Colaborador colaborador) {
        if (req == null) return null;
        RegistroDiario r = new RegistroDiario();
        r.setId(req.getId());
        r.setDataRegistro(req.getDataRegistro());
        r.setEscalaEmocional(req.getEscalaEmocional());
        r.setTempoTela(req.getTempoTela());
        r.setPausasRealizadas(req.getPausasRealizadas());
        r.setObservacoesColaborador(req.getObservacoesColaborador());
        r.setObservacoesAIDA(req.getObservacoesAIDA());
        r.setColaborador(colaborador);
        return r;
    }

    public static RegistroDiarioResponse toResponse(RegistroDiario r) {
        if (r == null) return null;
        RegistroDiarioResponse resp = new RegistroDiarioResponse();
        resp.setId(r.getId());
        resp.setDataRegistro(r.getDataRegistro());
        resp.setEscalaEmocional(r.getEscalaEmocional());
        resp.setTempoTela(r.getTempoTela());
        resp.setPausasRealizadas(r.getPausasRealizadas());
        resp.setObservacoesColaborador(r.getObservacoesColaborador());
        resp.setObservacoesAIDA(r.getObservacoesAIDA());
        if (r.getColaborador() != null) resp.setColaboradorCpf(r.getColaborador().getCpf());
        return resp;
    }
}
