package com.shinysyntax.aida.aida.dto.response;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

public class ColaboradorResponse {
    @Schema(description = "CPF do colaborador", example = "123.456.789-00")
    private String cpf;

    @Schema(description = "Nome completo", example = "João Silva")
    private String nome;

    @Schema(description = "E-mail", example = "joao.silva@example.com")
    private String email;

    @Schema(description = "Telefone", example = "(11) 98765-4321")
    private String telefone;

    @Schema(description = "Data de nascimento (ISO)", example = "1990-05-12")
    private LocalDate dataNascimento;

    @Schema(description = "Cargo", example = "Desenvolvedor(a)")
    private String cargo;

    @Schema(description = "Modalidade (enum)", example = "PRESENCIAL")
    private String modalidade;

    @Schema(description = "Data de admissão (ISO)", example = "2023-02-01")
    private LocalDate dataAdmissao;

    @Schema(description = "Problemas de saúde informados", example = "NÃO POSSUI PROBLEMAS DE SAÚDE")
    private String problemaSaude;

    @Schema(description = "Medicamento de uso diário", example = "NÃO FAZ USO DE MEDICAMENTO CONTROLADO")
    private String medicamentoUsoDiario;

    // getters and setters
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }
    public LocalDate getDataAdmissao() { return dataAdmissao; }
    public void setDataAdmissao(LocalDate dataAdmissao) { this.dataAdmissao = dataAdmissao; }
    public String getProblemaSaude() { return problemaSaude; }
    public void setProblemaSaude(String problemaSaude) { this.problemaSaude = problemaSaude; }
    public String getMedicamentoUsoDiario() { return medicamentoUsoDiario; }
    public void setMedicamentoUsoDiario(String medicamentoUsoDiario) { this.medicamentoUsoDiario = medicamentoUsoDiario; }
}
