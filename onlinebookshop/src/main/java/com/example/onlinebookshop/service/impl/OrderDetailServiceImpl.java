package com.example.onlinebookshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.model.Book;
import com.example.onlinebookshop.model.OrderDetail;
import com.example.onlinebookshop.model.OrderDetail.OrderDetailID;
import com.example.onlinebookshop.model.dto.OrderDetailDTO;
import com.example.onlinebookshop.repository.BookRepository;
import com.example.onlinebookshop.repository.OrderDetailRepository;
import com.example.onlinebookshop.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Autowired
	BookRepository bookRepository;

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

	@Override
	public List<OrderDetailDTO> convertToDTO(List<OrderDetail> orderDetails) {
		List<OrderDetailDTO> listDto = new ArrayList<OrderDetailDTO>();
		for (OrderDetail detail : orderDetails) {
			OrderDetailDTO dto = new OrderDetailDTO(detail.getOrder().getOrderId()
					, detail.getBook().getBookId(), detail.getQuantityOrder()
					, detail.getUnitPrice(), detail.getDiscount());
			Book book = bookRepository.findById(detail.getBook().getBookId()).get();
			
			dto.setBookName(book.getBookName());
			dto.setPhoto(book.getPhoto());
			
			listDto.add(dto);
		}
		return listDto;
	}

	@Override
	public Float getTotalPrice(List<OrderDetail> details) {
		float totalOrder = 0;
		for (OrderDetail detail : details) {
			Float itemPrice = detail.getQuantityOrder() * detail.getUnitPrice() * (1-detail.getDiscount());
			totalOrder += itemPrice;
		}
		return totalOrder;
	}
}