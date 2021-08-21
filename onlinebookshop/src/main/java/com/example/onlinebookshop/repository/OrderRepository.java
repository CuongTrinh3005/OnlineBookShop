package com.example.onlinebookshop.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onlinebookshop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findOrderByUser_Username(String username, Sort sort);
	List<Order> findAllByOrderByOrderDateDesc();
}
