package br.com.ecommerce.repositories;

import br.com.ecommerce.domain.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    Page<OrderItem> findAll(Pageable pageable);

}
