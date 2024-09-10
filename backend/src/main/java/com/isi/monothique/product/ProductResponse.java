package com.isi.monothique.product;


import com.isi.monothique.category.Category;
import com.isi.monothique.category.CategoryResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ProductResponse {
   private Integer id;
   private String name;
   private double price;
   private Integer quantity;
   private String description;
   private String categoryName;
//  private CategoryResponse category;
}
