package com.fmattaperdomo.tiendaAPI.dtos;

import com.fmattaperdomo.tiendaAPI.entities.Order;
import com.fmattaperdomo.tiendaAPI.entities.OrderItemPK;
import com.fmattaperdomo.tiendaAPI.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItemDto {
    private OrderItemPK id;
    @NotNull(message = "La cantidad no puede ser vacío")
    private Integer quantity;
    private Order order;
    private Product product;
}
