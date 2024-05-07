package com.example.cfinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cfinal.models.ClientOrdersModel;
import com.example.cfinal.utils.MyQueryRewriter;

public interface IClientOrderRepository extends JpaRepository<ClientOrdersModel, Long> {

	@Query(value = "SELECT o.* FROM client_orders o WHERE o.client_id = ?1", nativeQuery = true, queryRewriter = MyQueryRewriter.class)
	List<ClientOrdersModel> getOrdersByClientId(Long client_id);

	@Query(value = "SELECT o.* FROM client_orders o WHERE o.order_identifier = ?1", nativeQuery = true, queryRewriter = MyQueryRewriter.class)
	List<ClientOrdersModel> getByOrderIdentifier(String identifier);
}
