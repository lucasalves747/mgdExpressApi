package br.com.api.mgdexpress.MGD.EXPRESS.model.pedido;

import java.math.BigDecimal;
import java.util.List;

public record DadosPedidoPage(Long id, String nomePedido,String nomeEstabelecimento, BigDecimal valor,String localDestino,Status status) {
    public DadosPedidoPage(Pedido pedido) {
        this(pedido.getId(),pedido.getNomePedido(), pedido.getNomeEstabelecimento(), pedido.getValor(), pedido.getLocalDestino(),pedido.getStatus());
    }


}
