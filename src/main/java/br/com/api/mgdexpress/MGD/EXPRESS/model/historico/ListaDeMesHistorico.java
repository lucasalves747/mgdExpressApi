package br.com.api.mgdexpress.MGD.EXPRESS.model.historico;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

public record ListaDeMesHistorico(Month mes, float TotalRecebido, List<DadosHistoricoListMotoboy> historico) {
}
