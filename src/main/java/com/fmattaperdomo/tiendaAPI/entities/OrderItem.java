package com.fmattaperdomo.tiendaAPI.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "detalle_pedido")
public class OrderItem {
    @EmbeddedId
    private OrderItemPK id;

    @Column(name = "cantidad", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    @JsonBackReference(value = "Order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "producto_id", insertable = false, updatable = false)
    @JsonBackReference(value = "Product")
    private Product product;

}
