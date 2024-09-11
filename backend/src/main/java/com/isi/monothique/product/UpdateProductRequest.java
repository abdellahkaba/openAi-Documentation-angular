package com.isi.monothique.product;

public record UpdateProductRequest(
        Integer id,
        String name,
        double price,
        Integer quantity,
        String description,
        Integer categoryId
) {
}
