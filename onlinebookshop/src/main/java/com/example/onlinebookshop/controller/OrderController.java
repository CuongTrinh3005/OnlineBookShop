package com.example.onlinebookshop.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.onlinebookshop.exception.CustomException;
import com.example.onlinebookshop.model.Book;
import com.example.onlinebookshop.model.Order;
import com.example.onlinebookshop.model.OrderDetail;
import com.example.onlinebookshop.model.User;
import com.example.onlinebookshop.model.dto.BookDTO;
import com.example.onlinebookshop.service.impl.BookService;
import com.example.onlinebookshop.service.impl.OrderDetailService;
import com.example.onlinebookshop.service.impl.OrderService;
import com.example.onlinebookshop.service.impl.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class OrderController {
	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	BookService bookService;

	@GetMapping("orders")
	public List<Order> getAllOrdersByUsername(@RequestParam String username) {
		return orderService.findOrderByUsername(username);
	}

	@GetMapping("orders/{id}")
	public Optional<Order> retrieveOrder(@PathVariable Long id) {
		return orderService.findOrderById(id);
	}

	@PostMapping("orders")
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public ResponseEntity<Order> saveOrder(@Valid @RequestBody Order order, @RequestParam String username) {
		User user = userService.findByUserName(username).get();

		order.setUser(user);
		if (order.getOrderDate() == null)
			order.setOrderDate(new Date());

		Order newOrder = orderService.saveOrder(order);

		// Save order details
		Collection<OrderDetail> details = order.getOrderDetail();
		List<OrderDetail> listDetails = new ArrayList<OrderDetail>();
		listDetails.addAll(details);
		
		for (OrderDetail detail : listDetails) {
			detail.getOrderDetailID().setOrderId(newOrder.getOrderId());
			
			Book book = bookService.getBookById(detail.getOrderDetailID().getBookId()).get();
			
			if(book.getQuantity() < detail.getQuantityOrder())
				throw new CustomException("Not enough quantity");
			
			if(book.getAvailable()==null || !book.getAvailable())
				throw new CustomException("Book can not be sold");
			
			long quantityLeft = book.getQuantity() - detail.getQuantityOrder();
			book.setQuantity(quantityLeft);
			BookDTO bookDTO = bookService.convertBookToDTO(book);
			bookService.updateBook(bookDTO, bookDTO.getBookId());
			orderDetailService.saveOrderDetail(detail);
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newOrder.getOrderId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("orders/{id}")
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public ResponseEntity<Order> updateOrder(@Valid @RequestBody Order order, @PathVariable Long id) {
		Optional<Order> orderOpt = orderService.findOrderById(id);

		Order existedOrder = orderOpt.get();
		existedOrder.setOrderAddress(order.getOrderAddress());
		existedOrder.setDescription(order.getDescription());
		
		return new ResponseEntity<Order>(orderService.saveOrder(existedOrder), HttpStatus.OK);
	}
}