package br.com.ecommerce.repositories;

import br.com.ecommerce.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Page<User> findAll(Pageable pageable);

}
