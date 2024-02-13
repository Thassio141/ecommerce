package br.com.ecommerce.repositories;

import br.com.ecommerce.domain.entity.WishList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WishListRepository extends JpaRepository<WishList, UUID> {

    Page<WishList> findAll(Pageable pageable);

}
