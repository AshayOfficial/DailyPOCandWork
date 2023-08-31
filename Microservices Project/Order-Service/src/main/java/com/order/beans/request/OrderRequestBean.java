package com.order.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestBean {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "price")
    private Double price;
    @JsonProperty(value = "product_list")
    private List<String> productList;
    @JsonProperty(value = "is_order_cancelled")
    private Boolean isOrderCancelled;
}
