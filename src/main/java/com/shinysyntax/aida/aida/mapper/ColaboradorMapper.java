package com.shinysyntax.aida.aida.mapper;

import com.shinysyntax.aida.aida.dto.request.ColaboradorRequest;
import com.shinysyntax.aida.aida.dto.response.ColaboradorResponse;
import com.shinysyntax.aida.aida.entity.Colaborador;

public class ColaboradorMapper {

    public static Colaborador toEntity(ColaboradorRequest req) {
        if (req == null) return null;
        Colaborador c = new Colaborador();
        c.setCpf(req.getCpf());
        c.setNome(req.getNome());
        c.setEmail(req.getEmail());
        c.setTelefone(req.getTelefone());
        c.setDataNascimento(req.getDataNascimento());
        c.setCargo(req.getCargo());
        c.setModalidade(req.getModalidade());
        c.setDataAdmissao(req.getDataAdmissao());
        c.setProblemaSaude(req.getProblemaSaude());
        c.setMedicamentoUsoDiario(req.getMedicamentoUsoDiario());
        return c;
    }

    public static ColaboradorResponse toResponse(Colaborador c) {
        if (c == null) return null;
        ColaboradorResponse r = new ColaboradorResponse();
        r.setCpf(c.getCpf());
        r.setNome(c.getNome());
        r.setEmail(c.getEmail());
        r.setTelefone(c.getTelefone());
        r.setDataNascimento(c.getDataNascimento());
        r.setCargo(c.getCargo());
        r.setModalidade(c.getModalidade());
        r.setDataAdmissao(c.getDataAdmissao());
        r.setProblemaSaude(c.getProblemaSaude());
        r.setMedicamentoUsoDiario(c.getMedicamentoUsoDiario());
        return r;
    }
}
