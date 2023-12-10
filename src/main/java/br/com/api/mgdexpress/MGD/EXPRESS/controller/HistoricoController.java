package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.DadosHistoricoLista;
import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.DadosHistorico;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoRepository historicoRepository;

    @GetMapping("/motoboy/{idMotoboy}")
    public ResponseEntity buscarPeloIdMotoboy(@PageableDefault(size = 10) Pageable page,@PathVariable Long idMotoboy){
        var historicos = historicoRepository.BuscarPorIdMotoboy(page,idMotoboy).map(DadosHistoricoLista::new);
        return ResponseEntity.ok(historicos);
    }


    @GetMapping("/pedido/{idpedido}")
    public ResponseEntity<DadosHistorico> buscarPeloIdPedido(@PathVariable Long idpedido){
        var pedido = new DadosHistorico(historicoRepository.BuscarProIdPedido(idpedido));
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/gerente/id/{idGerente}")
    public ResponseEntity buscarPeloIdGerente(@PageableDefault(size = 10) Pageable page,@PathVariable Long idpedido){
        var historico = historicoRepository.BuscarProIdGerente(page,idpedido).map(DadosHistoricoLista::new);
        return ResponseEntity.ok(historico);
    }

    @GetMapping("/gerente/{email}")
    public ResponseEntity buscarPeloEmailGerente(@PageableDefault(size = 10) Pageable page,@PathVariable String email){
        var historico = historicoRepository.BuscarPorEmailGerente(email).stream().map(DadosHistoricoLista::new);
        return ResponseEntity.ok(historico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosHistoricoLista>> Listar(@PageableDefault(size = 10)Pageable pageable){
        var historicos = historicoRepository.findAll(pageable).map(DadosHistoricoLista::new);
        return ResponseEntity.ok(historicos);
    }



}
