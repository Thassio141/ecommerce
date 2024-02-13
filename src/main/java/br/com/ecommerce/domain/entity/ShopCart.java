package br.com.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shop_cart")
public class ShopCart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer quantity;

    @OneToOne
    private User user;

    @OneToOne(mappedBy = "shopCart")
    private Order order;

    @OneToMany(mappedBy = "shopCart", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();
}
