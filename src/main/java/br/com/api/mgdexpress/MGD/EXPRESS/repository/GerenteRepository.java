package br.com.api.mgdexpress.MGD.EXPRESS.repository;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GerenteRepository extends JpaRepository<Gerente,Long> {

    @Query("select g from Gerente g where g.email = :email")
    Gerente findByEmail(String email);
}
