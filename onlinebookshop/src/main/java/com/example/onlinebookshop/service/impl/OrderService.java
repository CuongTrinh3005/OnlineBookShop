package com.example.onlinebookshop.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.onlinebookshop.model.Order;

public interface OrderService {
	List<Order> findOrderByUsername(String username);
	Optional<Order> findOrderById(Long id);
	Order saveOrder(Order order);
}