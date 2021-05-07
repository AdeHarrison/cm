package com.waracle.cakemanager2.repository;

import com.waracle.cakemanager2.entity.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CakeRepository extends JpaRepository<Cake, UUID> {
}
