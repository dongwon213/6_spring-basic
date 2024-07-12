package com.example.e_firstpro.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@Getter
@ToString
public class ProductVO {

    private Long id;
    private String name;
    private double price;
    private String category;
    private String description;


    @Builder
    public ProductVO(Long id, String name, double price, String category, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;

    }
}
