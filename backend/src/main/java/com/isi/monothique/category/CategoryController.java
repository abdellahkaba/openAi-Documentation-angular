package com.isi.monothique.category;


import com.isi.monothique.common.PageResponse;
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

    @GetMapping
    public ResponseEntity<PageResponse<CategoryResponse>> findAllCategory(
            @RequestParam(name = "page",defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "3", required = false) int size
    ){
        return ResponseEntity.ok(service.findAllCategory(page,size));
    }
    @PutMapping("/{categorie-id}")
    public ResponseEntity<Void> updateCategory(
            @PathVariable("categorie-id") Integer id,
            @RequestBody @Valid UpdateCategoryRequest request
    ){
        request = new UpdateCategoryRequest(
                id,
                request.name(),
                request.description());
        service.updateCategory(request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/exists/{categorie-id}")
    public ResponseEntity<Boolean> existById(
            @PathVariable("categorie-id") Integer categorieId
    ){
        return ResponseEntity.ok(service.existById(categorieId));
    }
    @DeleteMapping("/{categorie-id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable("categorie-id") Integer categorieId){
        service.deleteCategory(categorieId) ;
        return ResponseEntity.accepted().build();
    }
}
