package com.product.service.impl;

import com.product.beans.request.ProductRequestBean;
import com.product.beans.response.CommonResponseBean;
import com.product.beans.response.ProductResponseBean;
import com.product.constants.ProductConstants;
import com.product.entity.Product;
import com.product.exceptions.AlreadyExistsException;
import com.product.exceptions.NoDataFoundException;
import com.product.mapper.ProductEntityToResponseMapper;
import com.product.mapper.ProductRequestBeanToEntityMapper;
import com.product.repo.ProductRepo;
import com.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public CommonResponseBean getAllProducts() {
        log.info("FETCHING ALL PRODUCTS");
        List<Product> productList = this.productRepo.findAll();

        if (productList.isEmpty())
            throw new NoDataFoundException(ProductConstants.NO_DATA_FOUND);

        List<ProductResponseBean> productResponseBeans = productList.stream()
                .filter(product -> product.getActiveStatus().equals(Boolean.TRUE))
                .map(product -> ProductEntityToResponseMapper.productEntityToResponseBean.apply(product))
                .toList();

        if (productResponseBeans.isEmpty())
            throw new NoDataFoundException(ProductConstants.NO_DATA_FOUND);

        log.info("ALL PRODUCTS FETCHED");
        return CommonResponseBean.builder()
                .status(Boolean.TRUE)
                .data(productResponseBeans)
                .build();
    }

    @Override
    public CommonResponseBean getProductById(Long productId) {
        log.info("FETCHING PRODUCT BY ID");
        Optional<Product> optionalProduct = this.productRepo.findById(productId);
        if (optionalProduct.isEmpty())
            throw new NoDataFoundException(ProductConstants.NO_DATA_FOUND);

        if (optionalProduct.get().getActiveStatus().equals(Boolean.FALSE))
            throw new NoDataFoundException(ProductConstants.NO_DATA_FOUND);

        log.info("PRODUCT FETCHED BY ID");
        return CommonResponseBean.builder()
                .status(Boolean.TRUE)
                .data(ProductEntityToResponseMapper.productEntityToResponseBean.apply(optionalProduct.get()))
                .build();

    }

    @Override
    public CommonResponseBean addProduct(ProductRequestBean productRequestBean) {
        log.info("ADDING PRODUCT");
        if (productRequestBean.getId() == null) {
            Product mappedProduct = ProductRequestBeanToEntityMapper.INSTANCE.productRequestToEntity(productRequestBean);
            mappedProduct.setCreatedDate(LocalDateTime.now());
            mappedProduct.setActiveStatus(Boolean.TRUE);
            Product product = this.productRepo.saveAndFlush(mappedProduct);
            log.info("PRODUCT ADDED");
            return CommonResponseBean.builder()
                    .status(Boolean.TRUE)
                    .data(ProductEntityToResponseMapper.productEntityToResponseBean.apply(product))
                    .build();
        }

        throw new AlreadyExistsException(ProductConstants.ALREADY_EXISTS);
    }

    @Override
    public CommonResponseBean updateProduct(ProductRequestBean productRequestBean) {
        log.info("UPDATING PRODUCT DETAILS");
        if (productRequestBean.getId() == null)
            throw new NoDataFoundException(ProductConstants.NO_DATA_FOUND);

        Optional<Product> optionalProduct = this.productRepo.findById(productRequestBean.getId());
        if (optionalProduct.isEmpty())
            throw new NoDataFoundException(ProductConstants.NO_DATA_FOUND);

        Product mappedProduct = ProductRequestBeanToEntityMapper.INSTANCE.productRequestToEntity(productRequestBean);
        mappedProduct.setUpdatedDate(LocalDateTime.now());
        mappedProduct.setActiveStatus(Boolean.TRUE);
        Product productSaved = this.productRepo.saveAndFlush(mappedProduct);

        log.info("PRODUCT DETAILS UPDATED");
        return CommonResponseBean.builder()
                .status(Boolean.TRUE)
                .data(ProductEntityToResponseMapper.productEntityToResponseBean.apply(productSaved))
                .build();
    }

    @Override
    public CommonResponseBean deleteProduct(Long productId) {
        log.info("DELETING PRODUCT");
        Optional<Product> optionalProduct = this.productRepo.findById(productId);
        if (optionalProduct.isEmpty())
            throw new NoDataFoundException(ProductConstants.NO_DATA_FOUND);

        Product product = optionalProduct.get();
        product.setActiveStatus(Boolean.FALSE);
        this.productRepo.saveAndFlush(product);

        log.info("PRODUCT DELETED SUCCESSFULLY");
        return CommonResponseBean.builder()
                .status(Boolean.TRUE)
                .message(ProductConstants.DATA_DELETED)
                .build();
    }
}
