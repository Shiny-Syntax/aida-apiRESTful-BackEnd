package com.shinysyntax.aida.aida.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

public class RegistroDiarioResponse {
    @Schema(description = "ID do registro", example = "1")
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Data do registro (dd/MM/yyyy)", example = "21/11/2025")
    private LocalDate dataRegistro;

    @Schema(description = "Escala emocional (0-10)", example = "7.5")
    private Double escalaEmocional;

    @Schema(description = "Tempo de tela em minutos", example = "120")
    private Integer tempoTela;

    @Schema(description = "Número de pausas realizadas", example = "3")
    private Integer pausasRealizadas;

    @Schema(description = "Observações do colaborador", example = "Tive um dia produtivo, sem maiores incidentes.")
    private String observacoesColaborador;

    @Schema(description = "Observações do AIDA", example = "Recomendado manter pausas de 5 minutos a cada 50 minutos.")
    private String observacoesAIDA;

    @Schema(description = "CPF do colaborador relacionado", example = "123.456.789-00")
    private String colaboradorCpf;

    @Schema(description = "Nome do colaborador relacionado", example = "João Silva")
    private String colaboradorNome;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDate dataRegistro) { this.dataRegistro = dataRegistro; }
    public Double getEscalaEmocional() { return escalaEmocional; }
    public void setEscalaEmocional(Double escalaEmocional) { this.escalaEmocional = escalaEmocional; }
    public Integer getTempoTela() { return tempoTela; }
    public void setTempoTela(Integer tempoTela) { this.tempoTela = tempoTela; }
    public Integer getPausasRealizadas() { return pausasRealizadas; }
    public void setPausasRealizadas(Integer pausasRealizadas) { this.pausasRealizadas = pausasRealizadas; }
    public String getObservacoesColaborador() { return observacoesColaborador; }
    public void setObservacoesColaborador(String observacoesColaborador) { this.observacoesColaborador = observacoesColaborador; }
    public String getObservacoesAIDA() { return observacoesAIDA; }
    public void setObservacoesAIDA(String observacoesAIDA) { this.observacoesAIDA = observacoesAIDA; }
    public String getColaboradorCpf() { return colaboradorCpf; }
    public void setColaboradorCpf(String colaboradorCpf) { this.colaboradorCpf = colaboradorCpf; }
    public String getColaboradorNome() { return colaboradorNome; }
    public void setColaboradorNome(String colaboradorNome) { this.colaboradorNome = colaboradorNome; }
}
