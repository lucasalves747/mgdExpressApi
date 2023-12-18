package br.com.api.mgdexpress.MGD.EXPRESS.model.gerenteTemporario;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.DadosGerente;
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
@Table(name = "gerenteTemporario")
public class GerenteTemporario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private String nomeEstebelecimento;
    private String localEstabelecimento;


    public GerenteTemporario(DadosGerente dadosGerente) {
        this.nome = dadosGerente.nome();
        this.telefone = dadosGerente.telefone();
        this.email = dadosGerente.email();
        this.senha = dadosGerente.senha();
        this.nomeEstebelecimento = dadosGerente.nomeEstabelecimento();
        this.localEstabelecimento = dadosGerente.localEstabelecimento();
    }
}
