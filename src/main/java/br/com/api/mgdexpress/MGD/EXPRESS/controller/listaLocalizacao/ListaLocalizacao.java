package br.com.api.mgdexpress.MGD.EXPRESS.controller.listaLocalizacao;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosLocalizacaoMotoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboyList;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.MotoboyRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.UserRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.services.TokenService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
    private UserRepository userRepository;

    @Autowired
    private MotoboyRepository motoboyRepository;

    @Getter
    private List<DadosMotoboyList> listaLocalizacao;
    int i = 0;
    @PostConstruct
    private void initialize() {
        System.out.println("Entrou no init com null");
        listaLocalizacao = new ArrayList<>(Collections.nCopies(motoboyRepository.encontrarMaiorId().intValue() + 1, null));
        motoboyRepository.findAllAtivos().forEach(motoboy -> listaLocalizacao.set(motoboy.getId().intValue(),new DadosMotoboyList(motoboy)));
    }

    public void setListaLocalizacao(DadosLocalizacaoMotoboy lista, Long id, String nome) {
        System.out.println("Up interno");
        if (listaLocalizacao.isEmpty()) {
            initialize();
        }

        var dadosMotoboyList = listaLocalizacao.get(id.intValue());
        listaLocalizacao.set(id.intValue(), new DadosMotoboyList(id, nome, lista.localizacao(), dadosMotoboyList.disponivel()));

        listaLocalizacao.forEach(System.out::println);
    }

    public void setStatus(Long id) {
        var dados = listaLocalizacao.get(id.intValue());
        if (dados.disponivel()) {
            listaLocalizacao.set(id.intValue(), new DadosMotoboyList(id, dados.nome(), dados.localizacao(), false));
        } else {
            listaLocalizacao.set(id.intValue(), new DadosMotoboyList(id, dados.nome(), dados.localizacao(), true));
        }
    }
}
