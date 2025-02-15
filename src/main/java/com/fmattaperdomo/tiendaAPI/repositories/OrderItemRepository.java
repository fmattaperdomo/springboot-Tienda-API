package com.fmattaperdomo.tiendaAPI.repositories;

import com.fmattaperdomo.tiendaAPI.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
