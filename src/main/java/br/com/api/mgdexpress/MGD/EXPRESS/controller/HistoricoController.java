package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.*;
import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.motoboy.DadosHistoricoMotoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.HistoricoRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    List<ListaDeMesHistorico> listaDeMesHistoricos = new ArrayList<>();
    List<DadosHistoricoListMotoboy> listaMotoboys = new ArrayList<>();
    float totalRecebido =0.0f;
    Month ultimoMes;

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private TokenService tokenService;

    @PreAuthorize("hasRole('ROLE_USER_MOTOBOY')")
    @GetMapping("/motoboy")
    public ResponseEntity<List<AnoComMesesHistorico>> buscarPeloIdMotoboy(@RequestHeader("Authorization") String header) {
        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);

        // Busca os históricos do motoboy
        List<DadosHistoricoListMotoboy> historicos = historicoRepository.BuscarMotoboy(id)
                .stream()
                .map(DadosHistoricoListMotoboy::new)
                .collect(Collectors.toList());

        // Agrupa os históricos por ano e mês
        Map<Integer, Map<Month, List<DadosHistoricoListMotoboy>>> historicosAgrupadosPorAnoEMes = historicos.stream()
                .collect(Collectors.groupingBy(
                        historico -> historico.dataEntrega().getYear(),
                        Collectors.groupingBy(historico -> historico.dataEntrega().getMonth())
                ));

        // Converte o mapa em uma lista de AnoComMesesHistorico
        List<AnoComMesesHistorico> anosComMesesHistoricos = historicosAgrupadosPorAnoEMes.entrySet().stream()
                .map(entryAno -> {
                    List<MesComHistorico> mesesComHistoricos = entryAno.getValue().entrySet().stream()
                            .map(entryMes -> new MesComHistorico(entryMes.getKey(),
                                    entryMes.getValue().stream().mapToDouble(h -> h.valor().doubleValue()).sum(),
                                    entryMes.getValue()))
                            .sorted(Comparator.comparing(MesComHistorico::mes))
                            .collect(Collectors.toList());

                    return new AnoComMesesHistorico(entryAno.getKey(), mesesComHistoricos);
                })
                .sorted(Comparator.comparing(AnoComMesesHistorico::ano))
                .collect(Collectors.toList());

        return ResponseEntity.ok(anosComMesesHistoricos);
    }


    @PreAuthorize("hasRole('ROLE_USER_MOTOBOY')")
    @GetMapping("/pedido/{idpedido}")
    public ResponseEntity<DadosHistoricoMotoboy> buscarPeloIdPedido(@PathVariable Long idpedido,@RequestHeader("Authorization") String header){
        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);

        var pedido = new DadosHistoricoMotoboy(historicoRepository.BuscarProIdPedido(idpedido,id));
        return ResponseEntity.ok(pedido);
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/gerente")
    public ResponseEntity buscarPeloEmailGerente(@PageableDefault(size = 10) Pageable page,@RequestHeader("Authorization") String header){
        var token = header.replace("Bearer ","");

        var subject = tokenService.getSubject(token);


        var historico = historicoRepository.BuscarPorEmailGerente(subject).stream().map(DadosHistoricoLista::new);
        return ResponseEntity.ok(historico);
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping
    public ResponseEntity<Page<DadosHistoricoLista>> Listar(@PageableDefault(size = 10)Pageable pageable){
        var historicos = historicoRepository.findAll(pageable).map(DadosHistoricoLista::new);
        return ResponseEntity.ok(historicos);
    }



}
