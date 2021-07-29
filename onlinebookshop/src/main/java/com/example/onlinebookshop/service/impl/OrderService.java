package com.example.onlinebookshop.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.onlinebookshop.model.Order;

public interface OrderService {
	List<Order> findOrderByUsername(String username);
	List<Order> findOrderByOrderDateDesc();
	Optional<Order> findOrderById(Long id);
	Order saveOrder(Order order);
	Order updateOrder(Order order, Long id);
}