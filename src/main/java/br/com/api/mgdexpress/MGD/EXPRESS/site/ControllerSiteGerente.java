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
    public ResponseEntity<HtmlPage> sucesso(){
        return ResponseEntity.ok(new HtmlPage(Sucesso.sucesso()));
    }

    @GetMapping("/home")
    public ResponseEntity<HtmlPage> home(){
        return ResponseEntity.ok(new HtmlPage(Home.home(url)));
    }

    @GetMapping("/criar")
    public ResponseEntity<HtmlPage> formulario(){
        return ResponseEntity.ok(new HtmlPage(Formulario.formulario();
    }

    @GetMapping("/meusPedidos/{email}")
    public ResponseEntity<HtmlPage> listarMeusPedidos(@PathVariable String email){
        return  ResponseEntity.ok(new HtmlPage(ListarMeusPedidos.listar()));
    }

    @GetMapping("/historico/{email}")
    public ResponseEntity<HtmlPage> listaHistoricos(@PathVariable String email){
        return ResponseEntity.ok(new HtmlPage(ListarHistorico.historocos(email)));
    }

    @GetMapping("historico/detalhes/{id}")
    public ResponseEntity<HtmlPage> detalharHistorico(@PathVariable Long id){
       var historico = historicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new HtmlPage(Detalhehistorico.detalhar(historico)));
    }

    @GetMapping("pedido/detalhes/{id}")
    public ResponseEntity<HtmlPage> detalharPedido(@PathVariable Long id){
        var pedido = pedidoRepository.getReferenceById(id);
        return ResponseEntity.ok(new HtmlPage(DetalhePedido.detalhar(pedido)));
    }
}
