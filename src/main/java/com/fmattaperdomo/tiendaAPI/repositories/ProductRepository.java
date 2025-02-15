package com.fmattaperdomo.tiendaAPI.repositories;

import com.fmattaperdomo.tiendaAPI.dtos.ProductResponseDto;
import com.fmattaperdomo.tiendaAPI.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :categoryId")
    Page<Product> findByCategoryId(Integer categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.productId NOT IN (SELECT o.id.producto_id FROM OrderItem o)")
    Page<Product> getProductsLagging(Pageable pageable);
}
