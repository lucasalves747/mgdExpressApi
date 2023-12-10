package br.com.api.mgdexpress.MGD.EXPRESS.repository;

import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.Historico;
import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico,Long> {
    @Query("select h from Historico h where h.motoboy.id = :idMotoboy")
    Page<Historico> BuscarPorIdMotoboy(Pageable page,Long idMotoboy);

    @Query("select h from Historico h where h.pedidoId = :idpedido ")
    Historico BuscarProIdPedido(Long idpedido);

    @Query("select h from Historico h where h.gerente.id = :idgerente ")
    Page<Historico> BuscarProIdGerente(Pageable page, Long idgerente);

    @Query("select h from Historico h where h.gerente.email = :email ")
    List<Historico> BuscarPorEmailGerente(String email);
}
