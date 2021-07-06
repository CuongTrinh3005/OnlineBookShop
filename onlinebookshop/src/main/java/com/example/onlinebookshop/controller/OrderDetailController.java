package com.example.onlinebookshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinebookshop.model.OrderDetail;
import com.example.onlinebookshop.model.OrderDetail.OrderDetailID;
import com.example.onlinebookshop.service.impl.OrderDetailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class OrderDetailController {
	@Autowired
	OrderDetailService orderDetailService;
	
	@GetMapping("order_details")
	public List<OrderDetail> getAllOrderDetails(){
		List<OrderDetailID> listOrderDetailId = new ArrayList<OrderDetailID>();
		OrderDetailID id1 = new OrderDetailID();
		id1.setBookId((long) 3);
		id1.setOrderId((long) 1);
		listOrderDetailId.add(id1);

		OrderDetailID id2 = new OrderDetailID();
		id2.setBookId((long) 3);
		id2.setOrderId((long) 2);
		listOrderDetailId.add(id2);
		
		return orderDetailService.findAllById(listOrderDetailId);
	}

}
