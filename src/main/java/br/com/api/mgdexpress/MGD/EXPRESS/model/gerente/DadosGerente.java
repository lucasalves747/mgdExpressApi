package br.com.api.mgdexpress.MGD.EXPRESS.model.gerente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosGerente(
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        @Email @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String nomeEstabelecimento,
        @NotBlank
        String localEstabelecimento) {
    public DadosGerente(Gerente gerente) {
        this(gerente.getNome(), gerente.getTelefone(), gerente.getEmail(), gerente.getSenha(),gerente.getNomeEstebelecimento(), gerente.getLocalEstabelecimento());
    }
}
