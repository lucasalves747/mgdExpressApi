package br.com.api.mgdexpress.MGD.EXPRESS.model.historico;
import java.time.Month;
import java.util.List;

public record MesComHistorico(String mes, double totalRecebido, List<DadosHistoricoListMotoboy> historicos) {
}
