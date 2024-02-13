package br.com.ecommerce.domain.entity;

import br.com.ecommerce.domain.enumerations.StatusOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private StatusOrder statusOrder;

    private Double totalPrice;

    private Date date;

    @ManyToOne
    private User user;

    @OneToOne
    private ShopCart shopCart;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItemList = new HashSet<>();
}
