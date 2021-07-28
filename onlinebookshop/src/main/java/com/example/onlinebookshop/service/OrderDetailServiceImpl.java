package com.example.onlinebookshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.model.OrderDetail;
import com.example.onlinebookshop.model.OrderDetail.OrderDetailID;
import com.example.onlinebookshop.repository.OrderDetailRepository;
import com.example.onlinebookshop.service.impl.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Override
	public Optional<OrderDetail> findById(OrderDetailID id) {
		OrderDetail orderDetail = orderDetailRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order detail id " + id + " not found"));
		return Optional.of(orderDetail);
	}

	@Override
	public List<OrderDetail> findAllById(List<OrderDetailID> listOrderDetailId) {
		return orderDetailRepository.findAllById(listOrderDetailId);
	}

	@Override
	public OrderDetail saveOrderDetail(OrderDetail detail) {
		return orderDetailRepository.save(detail);
	}

	@Override
	public List<OrderDetail> getAllOrderDetailByOrderId(Long orderId) {
		return orderDetailRepository.findByOrderDetailIDOrderId(orderId);
	}
}