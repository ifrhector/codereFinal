package com.example.cfinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cfinal.models.OrdersProductsModel;
import com.example.cfinal.utils.MyQueryRewriter;

public interface IOrderProductsRepository extends JpaRepository<OrdersProductsModel, Long> {

	@Query(value = "SELECT op.* FROM orders_products op WHERE op.order_id = ?1", nativeQuery = true, queryRewriter = MyQueryRewriter.class)
	List<OrdersProductsModel> getOrderProductsByOrderId(Long orderId);
}
