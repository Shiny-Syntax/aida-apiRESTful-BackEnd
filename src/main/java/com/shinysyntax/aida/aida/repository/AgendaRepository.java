package com.shinysyntax.aida.aida.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinysyntax.aida.aida.entity.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
