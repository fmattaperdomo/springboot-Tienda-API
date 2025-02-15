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
@Table(name = "productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer productId;

    @Column(name = "nombre", length = 50, nullable = false)
    private String name;

    @Column(name = "descripcion", length = 50, nullable = false)
    private String description;

    @Column(name = "precio", nullable = false)
    private Double price;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product")
    @JsonBackReference(value = "Product")
    private List<OrderItem> ordersItem;

    @Column(name = "categoria_id")
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "categoria_id", insertable = false, updatable = false)
    @JsonBackReference(value = "category")
    private Category category;
}
