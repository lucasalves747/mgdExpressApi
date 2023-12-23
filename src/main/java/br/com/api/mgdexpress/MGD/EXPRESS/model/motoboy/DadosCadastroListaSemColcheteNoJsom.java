package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;

import java.util.Objects;

public record DadosCadastroListaSemColcheteNoJsom(Long id, String nome, Localizacao localizacao, Boolean disponivel) {
    public DadosCadastroListaSemColcheteNoJsom (DadosMotoboyList dadosMotoboyList) {
        this(dadosMotoboyList.id(), dadosMotoboyList.nome(), dadosMotoboyList.localizacao(), dadosMotoboyList.disponivel());
    }


}
