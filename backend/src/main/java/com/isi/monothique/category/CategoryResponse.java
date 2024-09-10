package com.isi.monothique.category;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    private Integer id;
    private String name;
    private String description;

}
