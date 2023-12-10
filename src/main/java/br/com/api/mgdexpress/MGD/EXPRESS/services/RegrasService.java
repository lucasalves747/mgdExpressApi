package br.com.api.mgdexpress.MGD.EXPRESS.services;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Motoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.GerenteRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.HistoricoRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.MotoboyRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegrasService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private MotoboyRepository motoboyRepository;
    @Autowired
    private HistoricoRepository historicoRepository;
    @Autowired
    private GerenteRepository gerenteRepository;

    public void verificarSeMotoboyDisponivel(Motoboy motoboy){
        if (!motoboy.getDisponivel()){
            throw new RuntimeException("motoboy nao esta disponivel");
        }
    }

}
