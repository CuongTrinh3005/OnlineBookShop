package com.example.onlinebookshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.model.Order;
import com.example.onlinebookshop.repository.OrderRepository;
import com.example.onlinebookshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;

	@Override
	public List<Order> findOrderByUsername(String username) {
		return orderRepository.findOrderByUser_Username(username);
	}

	@Override
	public Optional<Order> findOrderById(Long id) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Order " + id + " not found"));
		return Optional.of(order);
	}

	@Override
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order updateOrder(Order order, Long id) {
		Optional<Order> orderOpt = findOrderById(id);

		Order existedOrder = orderOpt.get();
		existedOrder.setOrderAddress(order.getOrderAddress());
		existedOrder.setDescription(order.getDescription());
		
		return saveOrder(existedOrder);
	}

	@Override
	public List<Order> findOrderByOrderDateDesc() {
		return orderRepository.findAllByOrderByOrderDateDesc();
	}
	
	
}