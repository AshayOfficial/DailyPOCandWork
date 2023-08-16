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
    @JsonProperty(value = "date")
    private LocalDateTime date;
    @JsonProperty(value = "created_date")
    private LocalDateTime createdDate;
    @JsonProperty(value = "is_order_cancelled")
    private Boolean isOrderCancelled;
}
