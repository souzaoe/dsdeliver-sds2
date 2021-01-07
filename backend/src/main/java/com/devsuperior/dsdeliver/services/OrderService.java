package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.OrderStatus;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.OrderRepository;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

@Service // pode ser injetado em outros componentes
public class OrderService {
	
	// faz a injenção de resolução de dependencia 
	@Autowired
	private OrderRepository repository; 
	
	@Autowired
	private ProductRepository productRepository;  
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){ 
		List<Order> list = repository.findOrdersWithProducts(); 
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList()); 
	}
	
	@Transactional // não colocar o read one pois vai alterar o banco de dados, não é só de leitura
	public OrderDTO insert(OrderDTO dto){ 
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(),
				Instant.now(), OrderStatus.PENDING);
		// associando o pedido com o dto
		for (ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product); 
		}
		order = repository.save(order); 
		return new OrderDTO(order); 
	}
	
	@Transactional
	public OrderDTO SetDelivered(Long id){
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = repository.save(order); 
		return new OrderDTO(order); 		
	}
}
