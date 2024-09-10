package com.isi.monothique.product;


import com.isi.monothique.category.Category;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .price(request.price())
                .quantity(request.quantity())
                .description(request.description())
                .category(Category.builder()
                        .id(request.categoryId())
                        .build()
                )
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
//              .category(toCategoryResponse(product.getCategory()))
                .build();

    }
//    private CategoryResponse toCategoryResponse(Category category) {
//        if (category == null) {
//            return null; // Si la catégorie est null, on retourne null
//        }
//        return CategoryResponse.builder()
//                .id(category.getId())
//                .name(category.getName())
//                .description(category.getDescription())
//                .build();
//    }
}
