package br.com.api.mgdexpress.MGD.EXPRESS.site;

import br.com.api.mgdexpress.MGD.EXPRESS.repository.HistoricoRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.PedidoRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.site.pageService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/site/gerente")
public class ControllerSiteGerente {

    private String url = "https://mgdexpressapi-production.up.railway.app";

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public String mainHtml(){
        return MainHtml.html();
    }

    @GetMapping("/sucesso")
    public String sucesso(){
        return Sucesso.sucesso();
    }

    @GetMapping("/home")
    public String home(){

        return Home.home(url);
    }

    @GetMapping("/criar")
    public String formulario(){
        return Formulario.formulario();
    }

    @GetMapping("/meusPedidos/{email}")
    public String listarMeusPedidos(@PathVariable String email){
        return ListarMeusPedidos.listar();
    }

    @GetMapping("/historico/{email}")
    public String listaHistoricos(@PathVariable String email){
        return ListarHistorico.historocos(email);
    }

    @GetMapping("historico/detalhes/{id}")
    public String detalharHistorico(@PathVariable Long id){
       var historico = historicoRepository.getReferenceById(id);
        return Detalhehistorico.detalhar(historico);
    }

    @GetMapping("pedido/detalhes/{id}")
    public String detalharPedido(@PathVariable Long id){
        var pedido = pedidoRepository.getReferenceById(id);
        return DetalhePedido.detalhar(pedido);
    }
}
