package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.DadosHistoricoLista;
import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.DadosHistorico;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoRepository historicoRepository;

    @PreAuthorize("hasRole('ROLE_USER_MOTOBOY') OR hasRole('ROLE_USER_MASTER')")
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

    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/gerente/{email}")
    public ResponseEntity buscarPeloEmailGerente(@PageableDefault(size = 10) Pageable page,@PathVariable String email){
        var historico = historicoRepository.BuscarPorEmailGerente(email).stream().map(DadosHistoricoLista::new);
        return ResponseEntity.ok(historico);
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping
    public ResponseEntity<Page<DadosHistoricoLista>> Listar(@PageableDefault(size = 10)Pageable pageable){
        var historicos = historicoRepository.findAll(pageable).map(DadosHistoricoLista::new);
        return ResponseEntity.ok(historicos);
    }



}
