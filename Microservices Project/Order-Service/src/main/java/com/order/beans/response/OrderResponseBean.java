package com.order.beans.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseBean {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "price")
    private Double price;
    @JsonProperty(value = "product_list")
    private List<String> productList;
    @JsonProperty(value = "order_date")
    private String orderDate;
    @JsonProperty(value = "is_order_cancelled")
    private Boolean isOrderCancelled;
}
