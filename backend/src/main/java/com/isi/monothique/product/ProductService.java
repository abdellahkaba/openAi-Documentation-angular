package com.isi.monothique.product;


import com.isi.monothique.category.CategoryRepository;
import com.isi.monothique.common.PageResponse;
import com.isi.monothique.exception.CategoryNotFoundException;
import com.isi.monothique.exception.NameConflictException;
import com.isi.monothique.exception.ProductNotFoundException;
import io.micrometer.common.util.StringUtils;
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
    private final CategoryRepository categoryRepository;

    public Integer newProduct(ProductRequest request) {
        if (repository.findByName(request.name()).isPresent()){
            throw new NameConflictException("Cet Produit existe deja");
        }
        var category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Catégorie non trouvée avec l'ID: "));
        var product = mapper.toProduct(request);
        product.setCategory(category); // Associer la catégorie au produit
        product = repository.save(product);
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

    public ProductResponse findProductById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException("Aucun produit avec ce ID:: " + productId));
    }

    public Boolean existById(Integer id){
        return repository.findById(id)
                .isPresent();
    }

    public void deleteProduct(Integer id) {
        if (!repository.existsById(id)){
            throw new ProductNotFoundException(
                    String.format("Produit non trouvée", id)
            );
        }
    }

    public void updateProduct(UpdateProductRequest request) {
        var product = repository.findById(request.id())
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Le product non trouvé ID:: %s", request.id())
                ));
        if (request.categoryId() != null) {
            var category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new CategoryNotFoundException("Catégorie non trouvée avec l'ID: " + request.categoryId()));
            product.setCategory(category); // Associer la nouvelle catégorie au produit
        }
        mergeProduct(product,request);
        repository.save(product);
    }

    private void mergeProduct(Product product, UpdateProductRequest request) {
        if (StringUtils.isNotBlank(request.name()) &&
                !request.name().equals(product.getName()) &&
                repository.findByName(request.name()).isPresent()){
            throw new NameConflictException("Cet produit existe deja !");
        }
        if(StringUtils.isNotBlank(request.name())){
            product.setName(request.name());
        }
        if (request.price() != 0){
            product.setPrice(request.price());
        }
        if (request.quantity() != null){
            product.setQuantity(request.quantity());
        }
        if (StringUtils.isNotBlank(request.description())){
            product.setDescription(request.description());
        }
    }
}
