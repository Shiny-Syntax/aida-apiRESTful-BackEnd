package com.shinysyntax.aida.aida.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class AgendaResponse {
    @Schema(description = "ID da atividade", example = "1")
    private Long id;

    @Schema(description = "Tipo da atividade", example = "REUNIÃO")
    private String tipo;

    @Schema(description = "Descrição da atividade", example = "Revisão de sprint")
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Schema(description = "Data e hora (dd/MM/yyyy HH:mm)", example = "21/11/2025 10:00")
    private LocalDateTime dataHora;

    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("data_conclusao")
    @Schema(description = "Data de conclusão (ISO)", example = "2025-11-21")
    private LocalDate dataConclusao;

    @Schema(description = "Prioridade (enum)", example = "ALTA")
    private String prioridade;

    @Schema(description = "Plataforma / local", example = "Zoom")
    private String plataforma;

    @Schema(description = "Status da atividade", example = "PENDENTE")
    private String status;

    @Schema(description = "CPF do colaborador relacionado", example = "123.456.789-00")
    private String colaboradorCpf;

    @Schema(description = "Nome do colaborador relacionado", example = "João Silva")
    private String colaboradorNome;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public LocalDate getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(LocalDate dataConclusao) { this.dataConclusao = dataConclusao; }
    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }
    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getColaboradorCpf() { return colaboradorCpf; }
    public void setColaboradorCpf(String colaboradorCpf) { this.colaboradorCpf = colaboradorCpf; }
    public String getColaboradorNome() { return colaboradorNome; }
    public void setColaboradorNome(String colaboradorNome) { this.colaboradorNome = colaboradorNome; }
}
