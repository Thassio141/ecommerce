package br.com.ecommerce.repositories;

import br.com.ecommerce.domain.entity.Address;
import br.com.ecommerce.domain.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    Page<Address> findAll(Pageable pageable);
}
