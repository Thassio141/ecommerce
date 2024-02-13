package br.com.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String street;

    private String neighborhood;

    private Integer houseNumber;

    private String complement;

    private String zipCode;

    private String city;

    private String state;

    private String country;

    @ManyToOne
    private User user;

}
