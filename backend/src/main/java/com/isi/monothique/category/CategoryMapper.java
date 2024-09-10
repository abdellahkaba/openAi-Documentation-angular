package com.isi.monothique.category;


import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {


    public Category toCategory(CategoryRequest request) {
        if (request == null){
            return null;
        }

        return Category.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .build();
    }

    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
