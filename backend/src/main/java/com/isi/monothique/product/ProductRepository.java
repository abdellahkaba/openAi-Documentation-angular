package com.isi.monothique.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    Optional<Product> findByName(String name);

    @Query("""
            SELECT product
            FROM Product product
            """)
    Page<Product> findAllDisplayableProducts(Pageable pageable);
}
