package br.com.api.mgdexpress.MGD.EXPRESS.repository;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenteRepository extends JpaRepository<Gerente,Long> {
}
