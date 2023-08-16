package com.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.beans.request.OrderRequestBean;
import com.order.beans.response.CommonResponseBean;
import com.order.constants.OrderConstants;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = OrderConstants.REQUEST_MAPPING_URL)
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<CommonResponseBean> getAllOrders() {
        return ResponseEntity.ok(this.orderService.getALlOrders());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<CommonResponseBean> getOrderById(@PathVariable("order-id") Long orderId) {
        return ResponseEntity.ok(this.orderService.getOrderById(orderId));
    }

    @PostMapping
    public ResponseEntity<CommonResponseBean> placeOrder(@RequestBody OrderRequestBean orderRequestBean) throws JsonProcessingException {
        return ResponseEntity.ok(this.orderService.placeOrder(orderRequestBean));
    }

    @PutMapping ResponseEntity<CommonResponseBean> updateOrder(@RequestBody OrderRequestBean orderRequestBean) {
        return ResponseEntity.ok(this.orderService.updateOrder(orderRequestBean));
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity<CommonResponseBean> cancelOrder(@PathVariable("order-id") Long orderId) {
        return ResponseEntity.ok(this.orderService.cancelOrder(orderId));
    }
}
