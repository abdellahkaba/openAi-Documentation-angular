package com.isi.monothique.product;


import com.isi.monothique.common.PageResponse;
import com.isi.monothique.exception.NameConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer newProduct(ProductRequest request) {
        if (repository.findByName(request.name()).isPresent()){
            throw new NameConflictException("Cet Produit existe deja");
        }
        var product = this.repository.save(mapper.toProduct(request));
        return product.getId();

    }

    public PageResponse<ProductResponse> findAllProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = repository.findAllDisplayableProducts(pageable);
        List<ProductResponse> productResponses = products
                .map(mapper::toProductResponse)
                .toList();
        return new PageResponse<>(
                productResponses,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isFirst(),
                products.isLast()
        );
    }
}
