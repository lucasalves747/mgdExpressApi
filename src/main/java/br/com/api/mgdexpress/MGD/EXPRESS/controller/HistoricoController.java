package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.*;
import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.motoboy.DadosHistoricoMotoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.HistoricoRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.service.MesUtil;
import br.com.api.mgdexpress.MGD.EXPRESS.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private TokenService tokenService;

    @PreAuthorize("hasRole('ROLE_USER_MOTOBOY')")
    @GetMapping("/motoboy")
    public ResponseEntity<List<AnoComMesesHistorico>> buscarPeloIdMotoboy(@RequestHeader("Authorization") String header) {
        var token = header.replace("Bearer ", "");
        var id = tokenService.getId(token);

        // Busca os históricos do motoboy
        List<DadosHistoricoListMotoboy> historicos = historicoRepository.BuscarMotoboy(id)
                .stream()
                .map(DadosHistoricoListMotoboy::new)
                .toList();

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
                            .map(entryMes -> {
                                Month mes = entryMes.getKey();
                                String nomeMes = MesUtil.obterNomeMes(mes);
                                double somaValores = entryMes.getValue().stream().mapToDouble(h -> h.valor().doubleValue()).sum();
                                return new MesComHistorico(nomeMes, somaValores, entryMes.getValue());
                            })
                            .sorted(Comparator.comparing(MesComHistorico::mes))
                            .collect(Collectors.toList());

                    return new AnoComMesesHistorico(entryAno.getKey(), mesesComHistoricos);
                })
                .sorted(Comparator.comparing(AnoComMesesHistorico::ano))
                .collect(Collectors.toList());


        return ResponseEntity.ok(anosComMesesHistoricos);

    }




    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/gerente")
    public ResponseEntity buscarPeloEmailGerente(@PageableDefault(size = 10) Pageable page,@RequestHeader("Authorization") String header){
        var token = header.replace("Bearer ","");

        var subject = tokenService.getSubject(token);

        return ResponseEntity.ok(historicoRepository.BuscarPorEmailGerente(subject).stream().map(DadosHistoricoLista::new));

    }

    @PreAuthorize("hasRole('ROLE_USER_MOTOBOY')")
    @GetMapping("/pedido/{idpedido}")
    public ResponseEntity<DadosHistoricoMotoboy> buscarPeloIdPedido(@PathVariable Long idpedido){
        return ResponseEntity.ok(new DadosHistoricoMotoboy(historicoRepository.getReferenceById(idpedido)));
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping
    public ResponseEntity<Page<DadosHistoricoLista>> Listar(@PageableDefault(size = 10)Pageable pageable){
        var historicos = historicoRepository.findAll(pageable).map(DadosHistoricoLista::new);
        return ResponseEntity.ok(historicos);
    }

    public static String obterNomeMes(Month month) {
        return month.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
    }



}
