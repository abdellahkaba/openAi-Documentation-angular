package com.isi.monothique.product;


import com.isi.monothique.category.CategoryResponse;
import com.isi.monothique.category.UpdateCategoryRequest;
import com.isi.monothique.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
@Tag(name = "Products")
public class ProductController {

    private final ProductService service ;

    @PostMapping
    public ResponseEntity<Integer> newProduct(
         @Valid @RequestBody ProductRequest request
    ){
        return ResponseEntity.ok(service.newProduct(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> findAllProduct(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "3", required = false) int size
    ){
        return ResponseEntity.ok(service.findAllProduct(page,size));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findProductById(
            @PathVariable("product-id")  Integer productId
    ){
        return ResponseEntity.ok(service.findProductById(productId));
    }

    @GetMapping("/exists/{product-id}")
    public ResponseEntity<Boolean> existById(
            @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.ok(service.existById(productId));
    }
    @DeleteMapping("/{product-id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable("product-id") Integer productId){
        service.deleteProduct(productId); ;
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{product-id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable("product-id") Integer id,
            @RequestBody @Valid UpdateProductRequest request
    ){
        request = new UpdateProductRequest(
                id,
                request.name(),
                request.price(),
                request.quantity(),
                request.description(),
                request.categoryId()
                );
        service.updateProduct(request);
        return ResponseEntity.accepted().build();
    }
}
