package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;

import jakarta.validation.constraints.NotBlank;

public record DadosMotoboyMaster(
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
    public DadosMotoboyMaster(Motoboy motoboy) {
        this(motoboy.getId(), motoboy.getNome(), motoboy.getTelefone(),
                motoboy.getEmail(), null,motoboy.getCpf(),motoboy.getGrauDeParentescoContatoEmergencia(), motoboy.getTelefoneEmergencia(), motoboy.getChavePix());
    }
}
