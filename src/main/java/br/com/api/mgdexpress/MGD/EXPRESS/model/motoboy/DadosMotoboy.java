package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;

public record DadosMotoboy(
        Long id,
        String nome,
        String telefone,
        String email,
        Localizacao localizacao,
        Boolean disponivel,
        Boolean ativo) {
    public DadosMotoboy(Motoboy motoboy) {
        this(motoboy.getId(), motoboy.getNome(), motoboy.getTelefone(), motoboy.getEmail(), motoboy.getLocalizacao(),motoboy.getDisponivel(),motoboy.getAtivo());
    }
}
