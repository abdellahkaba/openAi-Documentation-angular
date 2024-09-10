package com.isi.monothique.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateCategoryRequest(
        Integer id,
        String name,
        String description
) {
}
