package com.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.beans.request.OrderRequestBean;
import com.order.beans.response.CommonResponseBean;

public interface OrderService {
    CommonResponseBean getALlOrders();
    CommonResponseBean getOrderById(Long orderId);
    CommonResponseBean placeOrder(OrderRequestBean orderRequestBean) throws JsonProcessingException;
    CommonResponseBean updateOrder(OrderRequestBean orderRequestBean);
    CommonResponseBean cancelOrder(Long orderId);
}
