package com.fmattaperdomo.tiendaAPI.services;

import com.fmattaperdomo.tiendaAPI.dtos.OrderDto;
import com.fmattaperdomo.tiendaAPI.dtos.OrderResponseDto;
import com.fmattaperdomo.tiendaAPI.dtos.ProductResponseDto;

public interface OrderService {
    OrderResponseDto getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    OrderDto updateOrderBystate(OrderDto orderDto, Integer orderId);
    OrderDto createOrder(OrderDto orderDto);
}
