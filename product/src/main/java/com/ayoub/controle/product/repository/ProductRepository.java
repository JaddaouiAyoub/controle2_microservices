package com.ayoub.controle.product.repository;

import com.ayoub.controle.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
