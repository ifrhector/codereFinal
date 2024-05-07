package com.example.cfinal.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cfinal.interfaces.IOrdersProducts;
import com.example.cfinal.models.ClientModel;
import com.example.cfinal.models.ClientOrdersModel;
import com.example.cfinal.models.OrdersProductsModel;
import com.example.cfinal.models.ProductModel;
import com.example.cfinal.repositories.IClientOrderRepository;
import com.example.cfinal.repositories.IClientRepository;
import com.example.cfinal.repositories.IOrderProductsRepository;
import com.example.cfinal.repositories.IProductRepository;
import com.example.cfinal.vo.ClientsOrdersVo;
import com.example.cfinal.vo.GenericResponseVo;
import com.example.cfinal.vo.OrdersVo;
import com.example.cfinal.vo.ProductsListVo;
import com.example.cfinal.vo.TimeZoneVo;

@Service
public class OrdersProductsService implements IOrdersProducts {

	@Autowired
	IClientRepository clientRepository;

	@Autowired
	IClientOrderRepository clientOrderRepository;

	@Autowired
	IOrderProductsRepository opRepository;

	@Autowired
	IProductRepository productRepository;

	@Autowired
	GetMxDateTime dateTime;

	private HttpStatus hs = null;

	@Override
	public ResponseEntity<GenericResponseVo> addNew(OrdersProductsModel orderProduct) {

		GenericResponseVo response = new GenericResponseVo();

		// Control from order status, if this is canceled or closed can't receive more
		// actions.
		if (!canAddOrderProducts(orderProduct.getOrderId().getId())) {

			hs = HttpStatus.BAD_REQUEST;

			response.setSuccess(false);
			response.setMessage("La orden se encuentra cerrada o cancelada, no se pueden agregar más productos");

			return new ResponseEntity<>(response, hs);
		}

		int stock = validateStock(orderProduct.getProduct().getId());

		if (orderProduct.getQuantity() <= stock) {

			// This is an external API to get data from TimeZone of any place of world
			// "api.timezonedb.com"
			TimeZoneVo timeZone = dateTime.getTimeZone();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(timeZone.getFormatted(), formatter);

			orderProduct.setPrice(orderProduct.getProduct().getPrice());

			double total = orderProduct.getQuantity() * orderProduct.getProduct().getPrice();

			orderProduct.setTotal(total);
			orderProduct.setMovementDate(dateTime);

			OrdersProductsModel saveProduct = opRepository.save(orderProduct);

			if (saveProduct != null) {
				hs = HttpStatus.ACCEPTED;
			} else {
				hs = HttpStatus.BAD_REQUEST;
			}

			int newStock = stock - orderProduct.getQuantity();

			updateStock(orderProduct.getProduct().getId(), newStock);
			updateClientOrder(orderProduct.getOrderId().getId(), total);

			response.setSuccess(true);
			response.setMessage("Producto agregado correctamente. Nuevo Stock: "
					+ saveProduct.getProduct().getDescription() + " es: " + newStock);
		} else {
			response.setSuccess(false);
			response.setMessage(
					"Producto no agregado. el Stock: " + orderProduct.getProduct().getDescription() + " es: " + stock);
			hs = HttpStatus.CONFLICT;
		}

		return new ResponseEntity<>(response, hs);
	}

	@Override
	public ResponseEntity<List<OrdersProductsModel>> getAll() {

		List<OrdersProductsModel> response = opRepository.findAll();

		if (response.isEmpty()) {
			hs = HttpStatus.BAD_REQUEST;
		} else {
			hs = HttpStatus.OK;
		}

		return new ResponseEntity<>(response, hs);
	}

	@Override
	public ResponseEntity<Optional<OrdersProductsModel>> getById(Long Id) {

		Optional<OrdersProductsModel> response = opRepository.findById(Id);

		if (response.isEmpty()) {
			hs = HttpStatus.BAD_REQUEST;
		} else {
			hs = HttpStatus.OK;
		}

		return new ResponseEntity<>(response, hs);
	}

	@Override
	public ResponseEntity<ClientsOrdersVo> getByClientId(Long id) {

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

	@Override
	public ResponseEntity<GenericResponseVo> update(Long id, OrdersProductsModel orderProduct) {

		GenericResponseVo response = new GenericResponseVo();

		if (opRepository.existsById(id)) {
			
			// Control from order status, if this is canceled or closed can't receive more
			// actions.
			if (!canAddOrderProducts(orderProduct.getOrderId().getId())) {

				hs = HttpStatus.CONFLICT;

				response.setSuccess(false);
				response.setMessage("La orden se encuentra cerrada o cancelada, no se pueden agregar más productos");

				return new ResponseEntity<>(response, hs);
			}
			
			Optional<OrdersProductsModel> opResponse = opRepository.findById(id);
			
			if(opResponse.get().getProduct().getId() != orderProduct.getProduct().getId()) {
				hs = HttpStatus.CONFLICT;

				response.setSuccess(false);
				response.setMessage("No se puede cambiar el producto, solo actualizar");

				return new ResponseEntity<>(response, hs);
			}
			
			if(opResponse.get().getOrderId().getId() != orderProduct.getOrderId().getId()) {
				hs = HttpStatus.CONFLICT;

				response.setSuccess(false);
				response.setMessage("No se puede cambiar la orden de venta");

				return new ResponseEntity<>(response, hs);
			}

			response = intentUpdate(id, orderProduct);

			if (response.isSuccess()) {
				hs = HttpStatus.ACCEPTED;
			} else {
				hs = HttpStatus.CONFLICT;
			}

		} else {
			response.setSuccess(false);
			response.setMessage("No se localiza el item a editar");
			hs = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<>(response, hs);
	}

	@Override
	public ResponseEntity<GenericResponseVo> delete(Long id) {
		// TODO Auto-generated method stub
		return null;
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

	//If product exist, return stock from product, else return 0
	private int validateStock(Long idProduct) {

		int result = 0;

		if (productRepository.existsById(idProduct)) {
			Optional<ProductModel> response = productRepository.findById(idProduct);
			result = response.get().getStock();
		}
		else {
			result = -1;
		}

		return result;
	}

	private void updateStock(Long idProduct, int newStock) {
		Optional<ProductModel> response = productRepository.findById(idProduct);
		response.get().setStock(newStock);
		productRepository.save(response.get());
	}

	private void updateClientOrder(Long idOrder, double amount) {
		Optional<ClientOrdersModel> order = clientOrderRepository.findById(idOrder);

		double t = order.get().getTotal() + amount;

		// This is an external API to get data from TimeZone of any place of world
		// "api.timezonedb.com"
		TimeZoneVo timeZone = dateTime.getTimeZone();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(timeZone.getFormatted(), formatter);

		order.get().setUpdateDateOrder(dateTime);
		order.get().setTotal(t);

		clientOrderRepository.save(order.get());

	}

	/**
	 * This function intent update product quantity and stock, if this is possible
	 * @param id
	 * @param orderProduct
	 * @return a GenericResponseVo with result
	 */
	private GenericResponseVo intentUpdate(Long id, OrdersProductsModel orderProduct) {

		GenericResponseVo response = new GenericResponseVo();

		int stock = validateStock(orderProduct.getProduct().getId());
		
		if(stock == -1) {
			response.setSuccess(false);
			response.setMessage("El producto a modificar no existe");
			
			return response;
		}
		
		int difference = 0;
		int newStock = 0;
		double newTotal = 0;

		// This is an external API to get data from TimeZone of any place of world
		// "api.timezonedb.com"
		TimeZoneVo timeZone = dateTime.getTimeZone();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(timeZone.getFormatted(), formatter);

		orderProduct.setMovementDate(dateTime);

		Optional<OrdersProductsModel> opResponse = opRepository.findById(id);
		
		if(orderProduct.getQuantity() <= 0) {
			difference = opResponse.get().getQuantity();
			newStock = stock + difference;
			
			opRepository.deleteById(id);
			
			double restTotal = opResponse.get().getQuantity() * orderProduct.getPrice();
			updateClientOrder(orderProduct.getOrderId().getId(), -restTotal);
			
			response.setSuccess(true);
			response.setMessage("El producto ha sido eliminado correctamente");
			
			return response;
		}

		// If origin quantity > new quantity sum products to stock
		if (opResponse.get().getQuantity() > orderProduct.getQuantity()) {
			difference = opResponse.get().getQuantity() - orderProduct.getQuantity();
			newStock = stock + difference;

			//new total = quantity * price original, no use the price in the stock
			newTotal = orderProduct.getQuantity() * orderProduct.getPrice();

			orderProduct.setId(id);
			orderProduct.setTotal(newTotal);
			orderProduct.setMovementDate(dateTime);

			OrdersProductsModel saveProduct = opRepository.save(orderProduct);

			updateStock(orderProduct.getProduct().getId(), newStock);
			updateClientOrder(orderProduct.getOrderId().getId(), newTotal);

			response.setSuccess(true);
			response.setMessage("Producto modificado correctamente. Nuevo Stock: "
					+ saveProduct.getProduct().getDescription() + " es: " + newStock);

		} else if (opResponse.get().getQuantity() < orderProduct.getQuantity()) { // On the contrary, rest items from
																					// stock
			if (orderProduct.getQuantity() <= stock) {

				newTotal = orderProduct.getQuantity() * orderProduct.getPrice();

				orderProduct.setId(id);
				orderProduct.setTotal(newTotal);
				orderProduct.setMovementDate(dateTime);

				OrdersProductsModel saveProduct = opRepository.save(orderProduct);

				newStock = stock - orderProduct.getQuantity();

				updateStock(orderProduct.getProduct().getId(), newStock);
				updateClientOrder(orderProduct.getOrderId().getId(), newTotal);

				response.setSuccess(true);
				response.setMessage("Producto modificado correctamente. Nuevo Stock: "
						+ saveProduct.getProduct().getDescription() + " es: " + newStock);
			} else {
				response.setSuccess(false);
				response.setMessage("Producto no modificado. El Stock: " + orderProduct.getProduct().getDescription()
						+ " es: " + stock + "lo requerido es: " + orderProduct.getQuantity());
			}
		}else {
			response.setSuccess(false);
			response.setMessage("Producto no modificado. El Stock: " + orderProduct.getProduct().getDescription()
					+ " es: " + stock + " lo requerido es: " + orderProduct.getQuantity());
		}

		return response;
	}

	private boolean canAddOrderProducts(Long idOrder) {
		boolean response = true;

		if (clientOrderRepository.existsById(idOrder)) {

			Optional<ClientOrdersModel> order = clientOrderRepository.findById(idOrder);

			if (order.get().getStatusId().getId() == 2 || order.get().getStatusId().getId() == 3) { // Order canceled or
																									// closed
				response = false;
			}
		}

		return response;
	}

}
