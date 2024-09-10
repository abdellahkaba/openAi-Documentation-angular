package com.isi.monothique.category;


import com.isi.monothique.exception.NameConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
