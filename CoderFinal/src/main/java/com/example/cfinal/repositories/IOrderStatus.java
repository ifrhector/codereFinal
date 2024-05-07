package com.example.cfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cfinal.models.CatOrderStatus;

public interface IOrderStatus extends JpaRepository<CatOrderStatus, Long> {

}
