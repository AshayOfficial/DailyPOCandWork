package com.order.mapper;

import com.order.beans.response.OrderResponseBean;
import com.order.entity.Order;

import java.util.function.Function;

public interface OrderEntityToResponseMapper {
    Function<Order, OrderResponseBean> orderEntityToOrderResponseBean = order ->
        OrderResponseBean.builder()
                .price(order.getOrderPrice())
                .orderDate(order.getOrderDate().toString())
                .isOrderCancelled(order.getIsOrderCancelled())
                .productList(order.getOrderProductList())
                .build();
}
