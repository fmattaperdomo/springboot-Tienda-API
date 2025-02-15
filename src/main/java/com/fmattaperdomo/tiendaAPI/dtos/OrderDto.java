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
public class OrderDto {
    private Integer orderId;
    @NotBlank(message = "Nombre de la orden no puede estar vacío")
    @NotNull
    private String name;
    @NotBlank(message = "Estado de la orden no puede estar vacío")
    @NotNull
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotNull(message = "El cliente no puede estar vacío")
    private Integer clientId;
    @NotNull(message = "El cliente no puede estar vacío")
    private Client client;
    @NotNull(message = "El cliente no puede estar vacío")
    private List<OrderItem> ordersItem;
}
