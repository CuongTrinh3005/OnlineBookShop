package com.example.onlinebookshop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.onlinebookshop.model.OrderStatus;
import com.example.onlinebookshop.model.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
		Sort sort = Sort.by(Sort.Direction.DESC, "orderDate");
		return orderRepository.findOrderByUser_Username(username, sort);
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
	public Order updateOrder(OrderDTO order, Long id) {
		Optional<Order> orderOpt = findOrderById(id);

		Order existedOrder = orderOpt.get();
		existedOrder.setOrderAddress(order.getOrderAddress());
		existedOrder.setDescription(order.getDescription());

		OrderStatus status= OrderStatus.getByState(order.getStatus());
		existedOrder.setState(status);
		
		return saveOrder(existedOrder);
	}

	@Override
	public List<Order> findOrderByOrderDateDesc() {
		return orderRepository.findAllByOrderByOrderDateDesc();
	}

	@Override
	public String composeConfirmOrder(List<OrderDetail> details, Date date, float totalCost) {
		String body = "You have an order on " + date.toString() + " with details: \r\n";
		body += "\r\n";
		body += "M?? ????n ?????t h??ng: " + details.get(0).getOrderDetailID().getOrderId() + "\r\n";
		body += "\r\n";
		for (OrderDetail detail : details) {
			body += "M?? s???n ph???m: " + detail.getOrderDetailID().getBookId() + "\r\n";
			body += "\r\n";
			Book product = bookRepository.getById(detail.getOrderDetailID().getBookId());
			body += "T??n s???n ph???m: " + product.getBookName() + "\r\n";
			body += "\r\n";
			body += "S??? l?????ng: " + detail.getQuantityOrder() + "\r\n";
			body += "\r\n";
			body += "????n gi??: " + detail.getUnitPrice() + " VN?? \r\n";
			body += "\r\n";
			if(detail.getDiscount() > 0){
				body += "Gi???m gi??: " + detail.getDiscount()*100 + "%" + "\r\n";
				body += "\r\n";
			}
			body += "----------------------------------------------------------- \r\n";
			body += "\r\n";
		}
		body += "T???ng th??nh ti???n: " + totalCost + " VN?? \r\n";
		body += "\r\n";
		body += "H??n h???nh ph???c v??? qu?? kh??ch !";
		return body;
	}

	@Override
	public OrderDTO convertToDTO(Order order) {
		return new OrderDTO(order.getOrderId(), order.getOrderDate(), order.getOrderAddress()
				, order.getDescription(), order.getUser().getUserName()
				, order.getUser().getFullName(), order.getState().name());
	}
}