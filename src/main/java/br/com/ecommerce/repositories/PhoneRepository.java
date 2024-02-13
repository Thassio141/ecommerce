package br.com.ecommerce.repositories;

import br.com.ecommerce.domain.entity.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {

    Page<Phone> findAll(Pageable pageable);

}
