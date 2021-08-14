package com.example.onlinebookshop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.model.Book;
import com.example.onlinebookshop.model.Order;
import com.example.onlinebookshop.model.OrderDetail;
import com.example.onlinebookshop.repository.BookRepository;
import com.example.onlinebookshop.repository.OrderRepository;
import com.example.onlinebookshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	BookRepository bookRepository;

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

	@Override
	public String composeConfirmOrder(List<OrderDetail> details, Date date, float totalCost) {
		String body = "You have an order on " + date.toString() + " with details: <br>";
		body += "<br>";
		body += "Mã đơn đặt hàng: " + details.get(0).getOrderDetailID().getOrderId() + "<br>";
		body += "<br>";
		for (OrderDetail detail : details) {
			body += "Mã sản phẩm: " + detail.getOrderDetailID().getBookId() + "<br>";
			body += "<br>";
			Book product = bookRepository.getById(detail.getOrderDetailID().getBookId());
			body += "Tên sản phẩm: " + product.getBookName() + "<br>";
			body += "<br>";
			body += "Số lượng: " + detail.getQuantityOrder() + "<br>";
			body += "<br>";
			body += "Đơn giá: " + detail.getUnitPrice() + " VNĐ <br>";
			body += "<br>";
			if(detail.getDiscount() > 0){
				body += "Giảm giá: " + detail.getDiscount() + "<br>";
				body += "<br>";
			}
			body += "----------------------------------------------------------- <br>";
			body += "<br>";
		}
		body += "Tổng thành tiền: " + totalCost + " VNĐ <br>";
		body += "<br>";
		body += "Hân hạnh phục vụ quý khách !";
		return body;
	}
}