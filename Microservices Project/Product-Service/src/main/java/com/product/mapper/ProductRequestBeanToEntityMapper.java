package com.product.mapper;

import com.product.beans.request.ProductRequestBean;
import com.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface ProductRequestBeanToEntityMapper {

    ProductRequestBeanToEntityMapper INSTANCE = Mappers.getMapper(ProductRequestBeanToEntityMapper.class);

    @Mappings({
            @Mapping(target = "productId", source = "productRequestBean.id"),
            @Mapping(target = "productName", source = "productRequestBean.name"),
            @Mapping(target = "productPrice", source = "productRequestBean.price"),
            @Mapping(target = "productDescription", source = "productRequestBean.description"),
            @Mapping(target = "productStockQuantity", source = "productRequestBean.stockQuantity"),
            @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    })
    Product productRequestToEntity(ProductRequestBean productRequestBean);

    BiFunction<ProductRequestBean, Product, Product> updateProductDetails = (productRequestBean, product) -> {
        product.setProductName(productRequestBean.getName() == null ? product.getProductName() : productRequestBean.getName());
        product.setProductPrice(productRequestBean.getPrice() == null ? product.getProductPrice() : productRequestBean.getPrice());
        product.setProductDescription(productRequestBean.getDescription() == null ? product.getProductDescription() : productRequestBean.getDescription());
        product.setProductStockQuantity(productRequestBean.getStockQuantity() == null ? product.getProductStockQuantity() : productRequestBean.getStockQuantity());
        return product;
    };
}
