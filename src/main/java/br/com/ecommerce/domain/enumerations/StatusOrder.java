package br.com.ecommerce.domain.enumerations;

public enum StatusOrder {

    PROCESS_PAYMENT(1),
    PACKING(2),
    SHIPPING(3),
    DELIVERED(4);

    StatusOrder(Integer statusCode) {
    }
}
