package com.example.onlinebookshop.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.onlinebookshop.model.Order;
import com.example.onlinebookshop.model.OrderDetail;

public interface OrderService {
	List<Order> findOrderByUsername(String username);
	List<Order> findOrderByOrderDateDesc();
	Optional<Order> findOrderById(Long id);
	Order saveOrder(Order order);
	Order updateOrder(Order order, Long id);
	String composeConfirmOrder(List<OrderDetail> details, Date date, float totalCost);
}