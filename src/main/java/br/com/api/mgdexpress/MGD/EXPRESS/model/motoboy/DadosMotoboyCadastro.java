package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;

import jakarta.validation.constraints.NotBlank;

public record DadosMotoboyCadastro(
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String cpf,
        @NotBlank
        String grauParentescoContatoEmergencia,
        @NotBlank
        String telefoneEmergencia,
        @NotBlank
        String chavepix) {
    public DadosMotoboyCadastro(Motoboy motoboy) {
        this(motoboy.getId(), motoboy.getNome(), motoboy.getTelefone(),
                motoboy.getEmail(), null,motoboy.getCpf(),motoboy.getGrauDeParentescoContatoEmergencia(), motoboy.getTelefoneEmergencia(), motoboy.getChavePix());
    }
}
