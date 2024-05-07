package com.example.cfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cfinal.models.ProductModel;

public interface IProductRepository extends JpaRepository<ProductModel, Long> {

}
