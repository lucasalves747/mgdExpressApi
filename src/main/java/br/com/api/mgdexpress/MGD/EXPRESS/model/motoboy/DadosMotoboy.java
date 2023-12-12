package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Motoboy;
import jakarta.validation.constraints.NotBlank;

public record DadosMotoboy(
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        @NotBlank
        String email) {
    public DadosMotoboy(Motoboy motoboy) {
        this(motoboy.getId(), motoboy.getNome(), motoboy.getTelefone(), motoboy.getEmail());
    }
}
