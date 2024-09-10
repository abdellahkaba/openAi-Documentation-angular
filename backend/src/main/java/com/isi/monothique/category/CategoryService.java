package com.isi.monothique.category;


import com.isi.monothique.common.PageResponse;
import com.isi.monothique.exception.CategoryNotFoundException;
import com.isi.monothique.exception.NameConflictException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public Integer saveCategory(CategoryRequest request) {
        if (repository.findByName(request.name()).isPresent()){
            throw new NameConflictException("Cette categorie existe deja !");
        }
        var category = this.repository.save(mapper.toCategory(request));
        return category.getId();
    }

    public CategoryResponse findCategoryById(Integer categorieId) {
        return repository.findById(categorieId)
                .map(mapper::toCategoryResponse)
                .orElseThrow(() -> new CategoryNotFoundException("Aucune category avec ce ID:: " + categorieId));
    }

    public PageResponse<CategoryResponse> findAllCategory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = repository.findAllDisplayableCategories(pageable);
        List<CategoryResponse> categoryResponses = categories
                .map(mapper::toCategoryResponse)
                .toList();
        return new PageResponse<>(
                categoryResponses,
                categories.getNumber(),
                categories.getSize(),
                categories.getTotalElements(),
                categories.getTotalPages(),
                categories.isFirst(),
                categories.isLast()
        );
    }

    public void updateCategory(UpdateCategoryRequest request) {
        var category = repository.findById(request.id())
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("La categorie non trouvé ID:: %s", request.id())
                ));
        mergeCategory(category,request);
        repository.save(category);
    }

    private void mergeCategory(Category category, UpdateCategoryRequest request) {
        if (StringUtils.isNotBlank(request.name()) &&
                !request.name().equals(category.getName()) &&
                repository.findByName(request.name()).isPresent()){
            throw new NameConflictException("Cette categorie existe deja !");
        }

        if(StringUtils.isNotBlank(request.name())){
            category.setName(request.name());
        }
        if (StringUtils.isNotBlank(request.description())){
            category.setDescription(request.description());
        }
    }

}
