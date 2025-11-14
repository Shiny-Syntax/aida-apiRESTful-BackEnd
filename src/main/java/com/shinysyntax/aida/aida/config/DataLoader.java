package com.shinysyntax.aida.aida.config;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.shinysyntax.aida.aida.entity.Agenda;
import com.shinysyntax.aida.aida.entity.Colaborador;
import com.shinysyntax.aida.aida.entity.RegistroDiario;
import com.shinysyntax.aida.aida.repository.AgendaRepository;
import com.shinysyntax.aida.aida.repository.ColaboradorRepository;
import com.shinysyntax.aida.aida.repository.RegistroDiarioRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final ColaboradorRepository colaboradorRepository;
    private final AgendaRepository agendaRepository;
    private final RegistroDiarioRepository registroRepository;

    public DataLoader(ColaboradorRepository colaboradorRepository, AgendaRepository agendaRepository, RegistroDiarioRepository registroRepository) {
        this.colaboradorRepository = colaboradorRepository;
        this.agendaRepository = agendaRepository;
        this.registroRepository = registroRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // seed colaboradores
        Colaborador c1 = new Colaborador();
        c1.setCpf("11122233344");
        c1.setNome("Maria Silva");
        c1.setEmail("maria@example.com");
        c1.setTelefone("+5511999999999");
        c1.setDataNascimento(LocalDate.of(1990,1,15));
        c1.setCargo("Analista");

        Colaborador c2 = new Colaborador();
        c2.setCpf("22233344455");
        c2.setNome("Joao Pereira");
        c2.setEmail("joao@example.com");
        c2.setTelefone("+5511988888888");
        c2.setDataNascimento(LocalDate.of(1985,6,20));
        c2.setCargo("Desenvolvedor");

        colaboradorRepository.save(c1);
        colaboradorRepository.save(c2);

        // seed agenda
        Agenda a1 = new Agenda();
        a1.setTipo("Reunião");
        a1.setDescricao("Reunião de acompanhamento");
        a1.setDataHora(LocalDateTime.now().plusDays(1));
        a1.setPrioridade(com.shinysyntax.aida.aida.enums.Priority.ALTA);
        a1.setPlataforma("Zoom");
        a1.setStatus(com.shinysyntax.aida.aida.enums.AgendaStatus.AGENDADO);
        a1.setColaborador(c1);
        agendaRepository.save(a1);

        // seed registro
        RegistroDiario r1 = new RegistroDiario();
        r1.setDataRegistro(LocalDate.now());
        r1.setEscalaEmocional(5);
        r1.setTempoTela(3);
        r1.setPausasRealizadas(2);
        r1.setObservacoesColaborador("Tudo certo hoje.");
        r1.setColaborador(c1);
        registroRepository.save(r1);
    }
}
