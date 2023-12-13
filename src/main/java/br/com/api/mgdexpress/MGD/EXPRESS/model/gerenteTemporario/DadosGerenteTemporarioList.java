package br.com.api.mgdexpress.MGD.EXPRESS.model.gerenteTemporario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosGerenteTemporarioList(
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        @Email @NotBlank
        String email,
        @NotBlank
        String nomeEstabelecimento,
        @NotBlank
        String localEstabelecimento
) {

    public DadosGerenteTemporarioList(GerenteTemporario gerenteTemporario) {

        this(gerenteTemporario.getId(), gerenteTemporario.getNome(), gerenteTemporario.getTelefone(), gerenteTemporario.getEmail(), gerenteTemporario.getNomeEstebelecimento(), gerenteTemporario.getLocalEstabelecimento());
    }
}
