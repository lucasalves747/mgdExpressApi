package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;

import java.util.List;

public record DadosMotoboyList(Long id,String nome, Localizacao localizacao,Boolean disponivel,String emailGerente) {
    public DadosMotoboyList(Motoboy motoboy) {
        this(motoboy.getId(), motoboy.getNome(), motoboy.getLocalizacao(),motoboy.getDisponivel(),null);
    }
}
