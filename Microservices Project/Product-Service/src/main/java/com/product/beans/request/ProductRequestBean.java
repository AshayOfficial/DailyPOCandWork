package com.product.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestBean {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "price")
    private String price;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "stock_quantity")
    private Long stockQuantity;
}
