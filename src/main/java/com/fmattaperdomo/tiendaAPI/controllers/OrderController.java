package com.fmattaperdomo.tiendaAPI.controllers;

import com.fmattaperdomo.tiendaAPI.configurations.AppConstants;
import com.fmattaperdomo.tiendaAPI.dtos.OrderDto;
import com.fmattaperdomo.tiendaAPI.dtos.OrderResponseDto;
import com.fmattaperdomo.tiendaAPI.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public ResponseEntity<OrderResponseDto> getAllOrders(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCT_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ){
        OrderResponseDto orderResponseDto = orderService.getAllOrders(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto){
        OrderDto savedOrderDto = orderService.createOrder(orderDto);
        return new ResponseEntity<>(savedOrderDto, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrderBystate(@Valid @RequestBody OrderDto orderDto, @PathVariable Integer orderId){
        OrderDto updatedOrderDto = orderService.updateOrderBystate(orderDto, orderId);
        return new ResponseEntity<>(updatedOrderDto, HttpStatus.OK);
    }
}
