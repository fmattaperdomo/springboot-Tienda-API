package com.fmattaperdomo.tiendaAPI.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fmattaperdomo.tiendaAPI.entities.Order;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
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
public class ClientDto {
    private Integer clientId;
    @NotBlank(message = "Nombre del cliente no puede estar vacío")
    @NotNull
    private String name;
    @NotBlank(message = "Correo electrónico no puede estar vacío")
    @NotNull
    @Email
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private List<Order> orders;
}
