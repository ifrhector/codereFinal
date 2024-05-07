package com.example.cfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cfinal.models.ClientModel;

public interface IClientRepository extends JpaRepository<ClientModel, Long> {

}
