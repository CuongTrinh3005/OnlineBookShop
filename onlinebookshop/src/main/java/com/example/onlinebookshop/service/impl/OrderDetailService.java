package com.example.onlinebookshop.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.onlinebookshop.model.OrderDetail;
import com.example.onlinebookshop.model.Rating;
import com.example.onlinebookshop.model.OrderDetail.OrderDetailID;


public interface OrderDetailService {
	Optional<OrderDetail> findById(OrderDetailID id);
	List<OrderDetail> findAllById(List<OrderDetailID> listOrderDetailId);
	OrderDetail saveOrderDetail(OrderDetail detail);
	List<OrderDetail> getAllOrderDetailByOrderId(Long orderId);
}