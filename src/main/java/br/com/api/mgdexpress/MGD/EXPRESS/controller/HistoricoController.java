package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.DadosHistoricoListMotoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.DadosHistoricoLista;
import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.DadosHistorico;
import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.ListaDeMesHistorico;
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
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    public ResponseEntity<List<ListaDeMesHistorico>> buscarPeloIdMotoboy(@RequestHeader("Authorization") String header){

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);


        List<DadosHistoricoListMotoboy> historicos = new ArrayList<>();

        historicoRepository.BuscarMotoboy(id).forEach(item->{
            historicos.add(new DadosHistoricoListMotoboy(item));
        });

        ultimoMes = historicos.get(0).dataEntrega().getMonth();

        historicos.forEach(historico ->{

            if(historico.dataEntrega().getMonth() != ultimoMes){
                listaDeMesHistoricos.add( new ListaDeMesHistorico(ultimoMes,totalRecebido,listaMotoboys));
                ultimoMes =  historico.dataEntrega().getMonth();

                listaMotoboys.clear();
                totalRecebido = 0.0f;

                totalRecebido += historico.valor().floatValue();
                listaMotoboys.add(historico);
            }else{
                totalRecebido += historico.valor().floatValue();
                listaMotoboys.add(historico);
            }
        });
        return ResponseEntity.ok(listaDeMesHistoricos);
    }




    @GetMapping("/pedido/{idpedido}")
    public ResponseEntity<DadosHistorico> buscarPeloIdPedido(@PathVariable Long idpedido){
        var pedido = new DadosHistorico(historicoRepository.BuscarProIdPedido(idpedido));
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
