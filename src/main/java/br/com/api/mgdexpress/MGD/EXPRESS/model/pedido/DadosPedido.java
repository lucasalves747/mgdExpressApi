package br.com.api.mgdexpress.MGD.EXPRESS.model.pedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosPedido(
        Long id,
        @NotBlank
        String nomePedido,

        @NotBlank
        String localDestino,
        @NotNull
        BigDecimal valor,

        String observacao,
        @NotBlank
        String itensDoPedido) {
        public DadosPedido(Pedido pedido) {
                this(pedido.getId(),pedido.getNomePedido(), pedido.getLocalDestino(),pedido.getValor(), pedido.getObservacao(), pedido.getItensDoPedido());
        }
}
