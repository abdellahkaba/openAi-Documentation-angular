package com.isi.monothique.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        Integer id,
        @NotNull(message = "Le nom de category est requis")
        @NotEmpty(message = "Le nom de category est requis")
        String name,
        @NotNull(message = "Essaye de descrire la category ")
        @NotEmpty(message = "Essaye de descrire la category")
        String description
) {
}
