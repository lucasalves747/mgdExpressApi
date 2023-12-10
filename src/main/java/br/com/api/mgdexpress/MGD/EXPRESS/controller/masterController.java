package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboyList;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.GerenteRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.HistoricoRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.MotoboyRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("master")
public class masterController {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private MotoboyRepository motoboyRepository;
    @Autowired
    private HistoricoRepository historicoRepository;
    @Autowired
    private GerenteRepository gerenteRepository;

    @GetMapping
    public ResponseEntity ListarMotoboys(){
        return ResponseEntity.ok(gerenteRepository.findAll());
    }
}
