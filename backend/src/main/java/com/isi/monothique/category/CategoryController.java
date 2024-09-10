package com.isi.monothique.category;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
@Tag(name = "Category")
public class CategoryController {

    private final CategoryService service;
    @PostMapping
    public ResponseEntity<Integer> saveCategory (
            @Valid @RequestBody CategoryRequest request
    ){
        return ResponseEntity.ok(service.saveCategory(request));
    }
    @GetMapping("/{categorie-id}")
    public ResponseEntity<CategoryResponse> findCategoryById(
            @PathVariable("categorie-id")  Integer categorieId
    ){
        return ResponseEntity.ok(service.findCategoryById(categorieId));
    }
}
