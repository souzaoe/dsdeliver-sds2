package com.devsuperior.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsdeliver.entities.Product;

// repositorey é os objetos que fazem acesso ao banco de dados

public interface OrderRepository extends JpaRepository<Product, Long>{

}
