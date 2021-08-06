package com.project.starbucksapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.starbucksapi.model.Bebidas;

@Repository
public interface BebidasRepository extends JpaRepository<Bebidas, Long> {

}
