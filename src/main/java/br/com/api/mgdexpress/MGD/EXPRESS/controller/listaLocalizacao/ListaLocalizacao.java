package br.com.api.mgdexpress.MGD.EXPRESS.controller.listaLocalizacao;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosLocalizacaoMotoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboyList;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.MotoboyRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.UserRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.services.TokenService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@Scope("application")
public class ListaLocalizacao {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository useRepository;

    @Autowired
    private MotoboyRepository motoboyRepository;


    @Getter
    private List<DadosMotoboyList> listaLocalizacao = new ArrayList<>(Collections.nCopies(motoboyRepository.encontrarMaiorId().intValue()+1, null));

    public void setListaLocalizacao(DadosLocalizacaoMotoboy lista,Long id,String nome) {
        if(Objects.isNull(listaLocalizacao)) {
            System.out.println("entrou no if do nulo");
            motoboyRepository.findAllAtivos().forEach(motoboy -> {
                var d = new DadosMotoboyList(motoboy);
                listaLocalizacao.set(motoboy.getId().intValue(), d);
            });
        }

        var dadosMotoboyList = listaLocalizacao.get(id.intValue());
        listaLocalizacao.set(id.intValue(),new DadosMotoboyList(id,nome,lista.localizacao(),dadosMotoboyList.disponivel()));

    }

    public void setStatus(Long id){
        var dados = listaLocalizacao.get(id.intValue());
        if(dados.disponivel()) {
            listaLocalizacao.set(id.intValue(), new DadosMotoboyList(id, dados.nome(), dados.localizacao(), false));
        }else{
            listaLocalizacao.set(id.intValue(), new DadosMotoboyList(id,dados.nome(),dados.localizacao(),true));

        }
    }
}

