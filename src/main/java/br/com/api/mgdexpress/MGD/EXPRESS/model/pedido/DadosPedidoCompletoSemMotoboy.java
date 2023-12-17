package br.com.api.mgdexpress.MGD.EXPRESS.model.pedido;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.DadosGerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboy;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosPedidoCompletoSemMotoboy (
        Long id,
        @NotBlank
        String nomeEstabelecimento,
        @NotBlank
        String localOrigem,
        @NotBlank
        String localDestino,
        @NotNull
        BigDecimal valor,

        String observacao,
        @NotBlank
        String itensDoPedido,
        Status status,
        LocalDate dataCriacao,
        @NotNull @Valid
        DadosGerente dadosGerente
){

    public DadosPedidoCompletoSemMotoboy(Pedido pedido) {
        this(pedido.getId(), pedido.getNomeEstabelecimento(), pedido.getLocalOrigem(), pedido.getLocalDestino(), pedido.getValor(), pedido.getObservacao(), pedido.getItensDoPedido(), pedido.getStatus(), pedido.getDataCriacao(), new DadosGerente(pedido.getGerente()));
    }
}
