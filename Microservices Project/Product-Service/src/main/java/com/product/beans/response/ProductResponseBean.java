package com.product.beans.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseBean {
    @JsonProperty(value = "product_id")
    private Long productId;
    @JsonProperty(value = "product_name")
    private String productName;
    @JsonProperty(value = "product_price")
    private String productPrice;
    @JsonProperty(value = "product_description")
    private String productDescription;
    @JsonProperty(value = "product_stock_quantity")
    private Long productStockQuantity;
}
