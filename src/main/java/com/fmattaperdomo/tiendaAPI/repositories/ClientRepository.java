package com.fmattaperdomo.tiendaAPI.repositories;

import com.fmattaperdomo.tiendaAPI.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
