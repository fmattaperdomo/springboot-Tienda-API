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
@Table(name = "pedidos")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer orderId;

    @Column(name = "nombre", length = 50, nullable = false)
    private String name;

    @Column(name = "estado", length = 45, nullable = false)
    private String state;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order")
    @JsonBackReference(value = "Order")
    private List<OrderItem> ordersItem;

    @Column(name = "cliente_id")
    private Integer clientId;

    @ManyToOne
    @JoinColumn(name = "cliente_id", insertable = false, updatable = false)
    @JsonBackReference(value = "client")
    private Client client;
}
