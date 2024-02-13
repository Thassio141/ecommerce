package br.com.ecommerce.domain.dto;

import java.util.UUID;

public record ProductCreateDto(String name,
                               Integer quantity,
                               Double price,
                               String description,
                               byte[] image,
                               UUID category) {
}
