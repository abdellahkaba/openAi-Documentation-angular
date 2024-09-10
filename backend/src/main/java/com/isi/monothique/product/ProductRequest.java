package com.isi.monothique.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Le nom du produit est requis")
        @NotEmpty(message = "Le nom du produit est requis")
        String name,
        @NotNull(message = "Donner le prix de produit")
        double price,
        @NotNull(message = "Donner la quantité de produit")
        Integer quantity,
        @NotNull(message = "Essaye de decrire un le produit")
        @NotEmpty(message = "Essaye de decrire un le produit")
        String description,
        @NotNull(message = "Donner une categorie correspondante a ce produit")
        Integer categoryId



) {
}
