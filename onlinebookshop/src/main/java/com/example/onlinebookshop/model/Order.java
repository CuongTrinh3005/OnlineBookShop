package com.example.onlinebookshop.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date orderDate;

    @Column(name = "order_address")
    @NotBlank
    private String orderAddress;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Collection<OrderDetail> orderDetail;

    @ManyToOne
    @JoinColumn(name = "username")
    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderStatus state = OrderStatus.CREATED;

    public Order() {
        // TODO Auto-generated constructor stub
    }

    public Order(Long orderId, Date orderDate, String orderAddress, String description, Collection<OrderDetail> orderDetail, User user, OrderStatus state) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderAddress = orderAddress;
        this.description = description;
        this.orderDetail = orderDetail;
        this.user = user;
        this.state = state;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(Collection<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getState() {
        return state;
    }

    public void setState(OrderStatus state) {
        this.state = state;
    }
}