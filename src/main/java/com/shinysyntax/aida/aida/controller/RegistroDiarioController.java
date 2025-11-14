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

import com.shinysyntax.aida.aida.dto.request.RegistroDiarioRequest;
import com.shinysyntax.aida.aida.dto.response.RegistroDiarioResponse;
import com.shinysyntax.aida.aida.entity.Colaborador;
import com.shinysyntax.aida.aida.entity.RegistroDiario;
import com.shinysyntax.aida.aida.exception.ResourceNotFoundException;
import com.shinysyntax.aida.aida.mapper.RegistroDiarioMapper;
import com.shinysyntax.aida.aida.repository.ColaboradorRepository;
import com.shinysyntax.aida.aida.service.RegistroDiarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/registros")
@Validated
public class RegistroDiarioController {

    private final RegistroDiarioService service;
    private final ColaboradorRepository colaboradorRepository;

    public RegistroDiarioController(RegistroDiarioService service, ColaboradorRepository colaboradorRepository) {
        this.service = service; this.colaboradorRepository = colaboradorRepository;
    }

    @GetMapping
    public List<RegistroDiarioResponse> list() { return service.findAll().stream().map(RegistroDiarioMapper::toResponse).collect(Collectors.toList()); }

    @GetMapping("/{id}")
    public RegistroDiarioResponse get(@PathVariable Long id) { return RegistroDiarioMapper.toResponse(service.findById(id)); }

    @PostMapping
    public ResponseEntity<RegistroDiarioResponse> create(@Valid @RequestBody RegistroDiarioRequest req) {
        String cpf = Objects.requireNonNull(req.getColaboradorCpf(), "colaboradorCpf must not be null");
        Colaborador c = colaboradorRepository.findById(cpf)
            .orElseThrow(() -> new ResourceNotFoundException("Colaborador not found: " + cpf));
        RegistroDiario saved = service.create(RegistroDiarioMapper.toEntity(req, c));
        RegistroDiarioResponse resp = RegistroDiarioMapper.toResponse(Objects.requireNonNull(saved));
        URI uri = URI.create("/api/registros/" + resp.getId());
        Objects.requireNonNull(uri);
        return ResponseEntity.created(uri).body(resp);
    }

    @PutMapping("/{id}")
    public RegistroDiarioResponse update(@PathVariable Long id, @Valid @RequestBody RegistroDiarioRequest req) {
        Objects.requireNonNull(id, "id must not be null");
        String cpf = Objects.requireNonNull(req.getColaboradorCpf(), "colaboradorCpf must not be null");
        Colaborador c = colaboradorRepository.findById(cpf)
            .orElseThrow(() -> new ResourceNotFoundException("Colaborador not found: " + cpf));
        RegistroDiario updated = service.update(id, RegistroDiarioMapper.toEntity(req, c));
        return RegistroDiarioMapper.toResponse(Objects.requireNonNull(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
