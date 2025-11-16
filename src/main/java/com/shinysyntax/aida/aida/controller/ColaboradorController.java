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

import com.shinysyntax.aida.aida.dto.request.ColaboradorRequest;
import com.shinysyntax.aida.aida.dto.response.ColaboradorResponse;
import com.shinysyntax.aida.aida.entity.Colaborador;
import com.shinysyntax.aida.aida.mapper.ColaboradorMapper;
import com.shinysyntax.aida.aida.service.ColaboradorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/colaboradores")
@Validated
public class ColaboradorController {

    private final ColaboradorService service;

    public ColaboradorController(ColaboradorService service) { this.service = service; }

    @GetMapping
    public List<ColaboradorResponse> list() {
        return service.findAll().stream().map(ColaboradorMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{cpf}")
    public ColaboradorResponse get(@PathVariable Long cpf) { return ColaboradorMapper.toResponse(service.findByCpf(cpf)); }

    @PostMapping
    public ResponseEntity<ColaboradorResponse> create(@Valid @RequestBody ColaboradorRequest req) {
        Colaborador saved = service.create(ColaboradorMapper.toEntity(req));
        ColaboradorResponse resp = ColaboradorMapper.toResponse(Objects.requireNonNull(saved));
        URI uri = URI.create("/api/colaboradores/" + resp.getCpf());
        Objects.requireNonNull(uri);
        return ResponseEntity.created(uri).body(resp);
    }

    @PutMapping("/{cpf}")
    public ColaboradorResponse update(@PathVariable Long cpf, @Valid @RequestBody ColaboradorRequest req) {
        Colaborador updated = service.update(cpf, ColaboradorMapper.toEntity(req));
        return ColaboradorMapper.toResponse(updated);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable Long cpf) {
        service.delete(cpf);
        return ResponseEntity.noContent().build();
    }
}
