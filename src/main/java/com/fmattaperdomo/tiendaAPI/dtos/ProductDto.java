package com.fmattaperdomo.tiendaAPI.dtos;

import com.fmattaperdomo.tiendaAPI.entities.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ProductDto {
    private Integer productId;
    @NotBlank(message = "Nombre del producto no puede estar vacío")
    @NotNull
    private String name;
    @NotBlank(message = "Descripción del producto no puede estar vacío")
    @NotNull
    private String description;
    @NotNull(message = "El precio del producto no puede estar vacío")
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotNull(message = "La categoría no puede estar vacío")
    private Integer categoryId;
    private Category category;
    private List<OrderItem> ordersItem;
}
