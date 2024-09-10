package com.isi.monothique.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByName(String name);
    @Query("""
            SELECT category
            FROM Category category
            """)
    Page<Category> findAllDisplayableCategories(Pageable pageable);
}
