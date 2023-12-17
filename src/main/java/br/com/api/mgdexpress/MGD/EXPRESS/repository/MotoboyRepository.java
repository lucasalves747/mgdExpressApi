package br.com.api.mgdexpress.MGD.EXPRESS.repository;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Motoboy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MotoboyRepository extends JpaRepository<Motoboy,Long> {


    @Query("select m from Motoboy m where m.ativo = true and  m.disponivel = false")
    Page<Motoboy> findAllAtivosEmEntrega(Pageable page);

    @Query("select m from Motoboy m where m.ativo = true and  m.disponivel = true")
    Page<Motoboy> findAlldisponiveisAtivos(Pageable page);

    @Query("select m from Motoboy m where m.id in(select p.motoboy.id from Pedido p where p.status = ANDAMENTO and p.gerente.email =:email)")
    Page<Motoboy> findAllAtivosEmEntregaByGerente(Pageable page,String email);


    Motoboy findByEmail(String username);
}
