package com.fmattaperdomo.tiendaAPI.dtos;

import com.fmattaperdomo.tiendaAPI.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryResponseDto {
    private Integer categoryId;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Product> products;
}
