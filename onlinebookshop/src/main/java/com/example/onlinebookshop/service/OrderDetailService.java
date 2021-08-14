package com.example.onlinebookshop.service;

import java.util.List;
import java.util.Optional;

import com.example.onlinebookshop.model.OrderDetail;
import com.example.onlinebookshop.model.OrderDetail.OrderDetailID;
import com.example.onlinebookshop.model.dto.OrderDetailDTO;


public interface OrderDetailService {
	Optional<OrderDetail> findById(OrderDetailID id);
	List<OrderDetail> findAllById(List<OrderDetailID> listOrderDetailId);
	OrderDetail saveOrderDetail(OrderDetail detail);
	List<OrderDetail> getAllOrderDetailByOrderId(Long orderId);
	List<OrderDetailDTO> convertToDTO(List<OrderDetail> orderDetails);
	Float getTotalPrice(List<OrderDetail> details);
}