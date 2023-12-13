package br.com.api.mgdexpress.MGD.EXPRESS.repository;

import br.com.api.mgdexpress.MGD.EXPRESS.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String subject);
}
