package br.com.api.mgdexpress.MGD.EXPRESS.model.pedido;

import java.math.BigDecimal;
import java.util.List;

public record DadosPedidoPage(Long id, String nomeEstabelecimento, BigDecimal valor,String localDestino) {
    public DadosPedidoPage(Pedido pedido) {
        this(pedido.getId(), pedido.getNomeEstabelecimento(), pedido.getValor(), pedido.getLocalDestino());
    }


}
