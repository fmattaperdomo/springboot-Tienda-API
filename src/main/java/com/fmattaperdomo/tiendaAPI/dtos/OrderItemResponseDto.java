package com.fmattaperdomo.tiendaAPI.dtos;

import com.fmattaperdomo.tiendaAPI.entities.Order;
import com.fmattaperdomo.tiendaAPI.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItemResponseDto {
    private Integer id;
    private Integer quantity;
    private Order order;
    private Product product;
}
