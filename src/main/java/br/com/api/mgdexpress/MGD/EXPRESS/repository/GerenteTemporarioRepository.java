package br.com.api.mgdexpress.MGD.EXPRESS.repository;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerenteTemporario.GerenteTemporario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenteTemporarioRepository extends JpaRepository<GerenteTemporario,Long> {
}
