package br.com.api.mgdexpress.MGD.EXPRESS.model.pedido;

import java.math.BigDecimal;

public record DadosPedidoPageEmandamento(Long id, String nomePedido, BigDecimal valor, String localDestino) {
    public DadosPedidoPageEmandamento(Pedido pedido) {
        this(pedido.getId(), pedido.getNomePedido(), pedido.getValor(), pedido.getLocalDestino());
    }
}
