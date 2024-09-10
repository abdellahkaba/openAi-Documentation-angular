package com.isi.monothique.category;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
