package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;

import java.util.List;

public record DadosMotoboyList(Long id,String nome, Localizacao localizacao) {
    public DadosMotoboyList(Motoboy motoboy) {
        this(motoboy.getId(), motoboy.getNome(), motoboy.getLocalizacao());
    }
}
