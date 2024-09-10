package com.isi.monothique.product;


import com.isi.monothique.category.Category;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity

public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private double price;
    private Integer quantity;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
