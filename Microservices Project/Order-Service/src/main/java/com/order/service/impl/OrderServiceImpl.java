package com.order.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.beans.request.OrderRequestBean;
import com.order.beans.response.CommonResponseBean;
import com.order.beans.response.OrderResponseBean;
import com.order.constants.OrderConstants;
import com.order.entity.Order;
import com.order.exceptions.AlreadyExistsException;
import com.order.exceptions.NoDataFoundException;
import com.order.repo.OrderRepo;
import com.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public CommonResponseBean getALlOrders() {
        log.info("FETCHING ALL ORDERS");
        List<Order> orderList = this.orderRepo.findAll();
        if (orderList.isEmpty())
            throw new NoDataFoundException(OrderConstants.NO_DATA_FOUND_EXCEPTION_MESSAGE);

        List<OrderResponseBean> orderResponseBeanList = orderList.stream()
                .filter(order -> order.getIsOrderCancelled().equals(Boolean.FALSE))
                .map(order -> this.modelMapper.map(order, OrderResponseBean.class))
                .toList();

        if (orderResponseBeanList.isEmpty())
            throw new NoDataFoundException(OrderConstants.NO_DATA_FOUND_EXCEPTION_MESSAGE);

        log.info("ORDERS FETCHED SUCCESSFULLY");
        return CommonResponseBean.builder()
                .status(Boolean.TRUE)
                .data(orderResponseBeanList)
                .build();
    }

    @Override
    public CommonResponseBean getOrderById(Long orderId) {
        log.info("FETCHING ORDER BY ID");
        Optional<Order> optionalOrder = this.orderRepo.findById(orderId);
        if (optionalOrder.isEmpty())
            throw new NoDataFoundException(OrderConstants.NO_DATA_FOUND_EXCEPTION_MESSAGE);

        if (optionalOrder.get().getIsOrderCancelled().equals(Boolean.TRUE))
            throw new NoDataFoundException(OrderConstants.NO_DATA_FOUND_EXCEPTION_MESSAGE);

        log.info("ORDER FETCHED BY ID SUCCESSFUL");
        OrderResponseBean orderResponseBean = this.modelMapper.map(optionalOrder.get(), OrderResponseBean.class);
        return CommonResponseBean.builder()
                .status(Boolean.TRUE)
                .data(orderResponseBean)
                .build();
    }

    @Override
    public CommonResponseBean placeOrder(OrderRequestBean orderRequestBean) throws JsonProcessingException {
        log.info("PLACING ORDER");
        if (orderRequestBean.getId() != null)
            throw new AlreadyExistsException(OrderConstants.ALREADY_EXISTS_EXCEPTION_MESSAGE);

        Order order = this.modelMapper.map(orderRequestBean, Order.class);
        order.setOrderDate(LocalDateTime.now());
        order.setCreatedDate(LocalDateTime.now());
        Order orderSaved = this.orderRepo.save(order);
        if (orderSaved.getOrderPrice()== 14000)
            throw new NoDataFoundException("jjj");
        OrderResponseBean orderResponseBean = this.modelMapper.map(orderSaved, OrderResponseBean.class);

        log.info("ORDER PLACED SUCCESSFULLY");
        return CommonResponseBean.builder()
                .status(Boolean.TRUE)
                .data(orderResponseBean)
                .build();
    }

    @Override
    public CommonResponseBean updateOrder(OrderRequestBean orderRequestBean) {
        log.info("UPDATING ORDER");
        Optional<Order> optionalOrder = this.orderRepo.findById(orderRequestBean.getId());
        if (optionalOrder.isEmpty())
            throw new NoDataFoundException(OrderConstants.NO_DATA_FOUND_EXCEPTION_MESSAGE);

        Order order = this.modelMapper.map(orderRequestBean, Order.class);
        order.setOrderDate(LocalDateTime.now());
        order.setUpdatedDate(LocalDateTime.now());
        Order orderSaved = this.orderRepo.saveAndFlush(order);
        OrderResponseBean orderResponseBean = this.modelMapper.map(orderSaved, OrderResponseBean.class);

        log.info("ORDER UPDATED SUCCESSFULLY");
        return CommonResponseBean.builder()
                .status(Boolean.TRUE)
                .data(orderResponseBean)
                .build();
    }

    @Override
    public CommonResponseBean cancelOrder(Long orderId) {
        log.info("CANCELLING ORDER");
        Optional<Order> optionalOrder = this.orderRepo.findById(orderId);
        if (optionalOrder.isEmpty())
            throw new NoDataFoundException(OrderConstants.NO_DATA_FOUND_EXCEPTION_MESSAGE);

        optionalOrder.get().setIsOrderCancelled(Boolean.TRUE);
        this.orderRepo.saveAndFlush(optionalOrder.get());

        log.info("ORDER CANCELLED SUCCESSFULLY");
        return CommonResponseBean.builder()
                .status(Boolean.TRUE)
                .message("ORDER CANCELLED SUCCESSFULLY")
                .build();
    }
}
