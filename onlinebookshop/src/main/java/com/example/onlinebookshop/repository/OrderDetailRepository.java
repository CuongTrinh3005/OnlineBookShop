package com.example.onlinebookshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onlinebookshop.model.OrderDetail;

import com.example.onlinebookshop.model.OrderDetail.OrderDetailID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailID> {
	List<OrderDetail> findByOrderDetailIDOrderId(Long orderId);
}
