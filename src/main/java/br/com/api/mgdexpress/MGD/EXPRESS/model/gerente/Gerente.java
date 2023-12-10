package br.com.api.mgdexpress.MGD.EXPRESS.model.gerente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gerente")
public class Gerente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String nomeEstebelecimento;
    private String localEstabelecimento;


    public Gerente(DadosGerente dadosGerente) {
        this.nome = dadosGerente.nome();
        this.telefone = dadosGerente.telefone();
        this.email = dadosGerente.email();
        this.nomeEstebelecimento = dadosGerente.nomeEstabelecimento();
        this.localEstabelecimento = dadosGerente.localEstabelecimento();
    }
}
