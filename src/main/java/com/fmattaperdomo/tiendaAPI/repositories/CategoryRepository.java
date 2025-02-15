package com.fmattaperdomo.tiendaAPI.repositories;

import com.fmattaperdomo.tiendaAPI.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
