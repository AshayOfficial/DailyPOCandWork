package com.order.mapper;

import com.order.beans.request.OrderRequestBean;
import com.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface OrderRequestToEntityMapper {

    OrderRequestToEntityMapper INSTANCE = Mappers.getMapper(OrderRequestToEntityMapper.class);

    @Mappings({
            @Mapping(target = "orderId", source = "orderRequestBean.id"),
            @Mapping(target = "orderPrice", source = "orderRequestBean.price"),
            @Mapping(target = "orderProductList", source = "orderRequestBean.productList"),
            @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    })
    Order OrderRequestBeanToOrderEntity(OrderRequestBean orderRequestBean);

    BiFunction<OrderRequestBean, Order, Order> updateOrder = (orderRequestBean, order) -> {
        order.setOrderPrice(orderRequestBean.getPrice() == null ? order.getOrderPrice() : orderRequestBean.getPrice());
        order.setOrderProductList(orderRequestBean.getProductList() == null ? order.getOrderProductList() : orderRequestBean.getProductList());
        order.setIsOrderCancelled(order.getIsOrderCancelled().equals(orderRequestBean.getIsOrderCancelled()) ? order.getIsOrderCancelled() : orderRequestBean.getIsOrderCancelled());
        order.setUpdatedDate(LocalDateTime.now());
        return order;
    };
}
