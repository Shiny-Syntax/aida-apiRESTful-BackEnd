package com.shinysyntax.aida.aida.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinysyntax.aida.aida.dto.request.AgendaRequest;
import com.shinysyntax.aida.aida.dto.response.AgendaResponse;
import com.shinysyntax.aida.aida.entity.Agenda;
import com.shinysyntax.aida.aida.entity.Colaborador;
import com.shinysyntax.aida.aida.exception.ColaboradorNotFoundException;
import com.shinysyntax.aida.aida.mapper.AgendaMapper;
import com.shinysyntax.aida.aida.repository.ColaboradorRepository;
import com.shinysyntax.aida.aida.service.AgendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agenda")
@Validated
public class AgendaController {

    private final AgendaService service;
    private final ColaboradorRepository colaboradorRepository;

    public AgendaController(AgendaService service, ColaboradorRepository colaboradorRepository) {
        this.service = service; this.colaboradorRepository = colaboradorRepository;
    }

    @GetMapping
    public List<AgendaResponse> list() { return service.findAll().stream().map(AgendaMapper::toResponse).collect(Collectors.toList()); }

    @GetMapping("/{id}")
    public AgendaResponse get(@PathVariable Long id) { return AgendaMapper.toResponse(service.findById(id)); }

    @PostMapping
    public ResponseEntity<AgendaResponse> create(@Valid @RequestBody AgendaRequest req) {
        String cpf = Objects.requireNonNull(req.getColaboradorCpf(), "colaboradorCpf must not be null");
        Colaborador c = colaboradorRepository.findById(cpf)
            .orElseThrow(() -> new ColaboradorNotFoundException("Colaborador not found: " + cpf));
        Agenda saved = service.create(AgendaMapper.toEntity(req, c));
        AgendaResponse resp = AgendaMapper.toResponse(Objects.requireNonNull(saved));
        URI uri = URI.create("/api/agenda/" + resp.getId());
        Objects.requireNonNull(uri);
        return ResponseEntity.created(uri).body(resp);
    }

    @PutMapping("/{id}")
    public AgendaResponse update(@PathVariable Long id, @Valid @RequestBody AgendaRequest req) {
        Objects.requireNonNull(id, "id must not be null");
        String cpf = Objects.requireNonNull(req.getColaboradorCpf(), "colaboradorCpf must not be null");
        Colaborador c = colaboradorRepository.findById(cpf)
            .orElseThrow(() -> new ColaboradorNotFoundException("Colaborador not found: " + cpf));
        Agenda updated = service.update(id, AgendaMapper.toEntity(req, c));
        return AgendaMapper.toResponse(Objects.requireNonNull(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
