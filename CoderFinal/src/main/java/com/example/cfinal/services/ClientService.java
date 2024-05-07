package com.example.cfinal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cfinal.interfaces.IClient;
import com.example.cfinal.models.ClientModel;
import com.example.cfinal.models.ClientOrdersModel;
import com.example.cfinal.models.OrdersProductsModel;
import com.example.cfinal.repositories.IClientOrderRepository;
import com.example.cfinal.repositories.IClientRepository;
import com.example.cfinal.repositories.IOrderProductsRepository;
import com.example.cfinal.vo.ClientsOrdersVo;
import com.example.cfinal.vo.OrdersVo;
import com.example.cfinal.vo.ProductsListVo;

@Service
public class ClientService implements IClient {

	@Autowired
	IClientRepository clientRepository;

	@Autowired
	IClientOrderRepository clientOrderRepository;

	@Autowired
	IOrderProductsRepository opRepository;
	
	private HttpStatus hs = null;

	@Override
	public ResponseEntity<ClientModel> addNew(ClientModel client) {

		ClientModel response = clientRepository.save(client);

		if (response != null) {
			hs = HttpStatus.CREATED;
		} else {
			hs = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<>(response, hs);
	}

	@Override
	public List<ClientModel> getAll() {
		return clientRepository.findAll();
	}

	@Override
	public Optional<ClientModel> getById(Long id) {
		return clientRepository.findById(id);
	}

	@Override
	public ResponseEntity<ClientModel> update(Long id, ClientModel client) {

		if (clientRepository.existsById(id)) {
			client.setId(id);
			clientRepository.save(client);
			hs = HttpStatus.ACCEPTED;
		} else {
			hs = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<>(client, hs);
	}

	@Override
	public ResponseEntity<Optional<ClientModel>> delete(Long id) {
		
		Optional<ClientModel> response = null;

		if (clientRepository.existsById(id)) {
			response = clientRepository.findById(id);
			response.get().setActive(false);
			hs = HttpStatus.ACCEPTED;
		} else {
			hs = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<>(response, hs);
	}

	@Override
	public ResponseEntity<ClientsOrdersVo> getOrdersById(Long id) {

		ClientsOrdersVo response = new ClientsOrdersVo();

		Optional<ClientModel> client = null;

		if (clientRepository.existsById(id)) {
			client = clientRepository.findById(id);

			List<OrdersVo> orders = getOrdersByClient(id);

			response.setClientCountry(client.get().getCountry());
			response.setClientName(client.get().getClientName());
			response.setOrders(orders);

			hs = orders.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
		} else {
			hs = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<>(response, hs);
	}

	private List<OrdersVo> getOrdersByClient(Long id) {

		List<OrdersVo> orders = new ArrayList<>();

		List<ClientOrdersModel> clientOrders = clientOrderRepository.getOrdersByClientId(id);

		if (clientOrders.isEmpty()) {
			return orders;
		}

		for (ClientOrdersModel order : clientOrders) {
			
			OrdersVo o = new OrdersVo();
			
			o.setDateOrder(order.getMakeDateOrder());
			o.setOrder(order.getOrderIdentifier());
			o.setUpdateOrder(order.getUpdateDateOrder());
			o.setCanceledOrder(order.getCanceledDateOrder());
			o.setNotes(order.getNotes());
			
			double tFinal = order.getTotal();

			// GetList of products
			List<ProductsListVo> products = new ArrayList<>();

			if (clientOrderRepository.existsById(order.getId())) {

				List<OrdersProductsModel> response = opRepository.getOrderProductsByOrderId(order.getId());

				for (OrdersProductsModel orderProds : response) {
					ProductsListVo op = new ProductsListVo();

					Double t = orderProds.getPrice() * orderProds.getQuantity();

					op.setDateAdded(orderProds.getMovementDate().toString());
					op.setProductName(orderProds.getProduct().getDescription());
					op.setPrice(orderProds.getPrice());
					op.setQuantity(orderProds.getQuantity());
					op.setTotal(t);

					products.add(op);

				}
			}

			o.setProducts(products);
			o.setTotalOrder(tFinal);

			orders.add(o);
		}

		return orders;
	}

}
