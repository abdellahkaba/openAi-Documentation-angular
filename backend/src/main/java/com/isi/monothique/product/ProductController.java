package com.isi.monothique.product;


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
}
