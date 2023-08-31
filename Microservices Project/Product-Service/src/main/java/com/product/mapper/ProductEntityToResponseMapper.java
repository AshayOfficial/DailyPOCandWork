package com.product.mapper;

import com.product.beans.response.ProductResponseBean;
import com.product.entity.Product;

import java.util.function.Function;

public interface ProductEntityToResponseMapper {
    Function<Product, ProductResponseBean> productEntityToResponseBean = product ->
            ProductResponseBean.builder()
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .productDescription(product.getProductDescription())
                    .productPrice(product.getProductPrice())
                    .productStockQuantity(product.getProductStockQuantity())
                    .build();
}
