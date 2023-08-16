package com.product.service;

import com.product.beans.request.ProductRequestBean;
import com.product.beans.response.CommonResponseBean;

public interface ProductService {
    CommonResponseBean getAllProducts();
    CommonResponseBean getProductById(Long productId);
    CommonResponseBean addProduct(ProductRequestBean productRequestBean);
    CommonResponseBean updateProduct(ProductRequestBean productRequestBean);
    CommonResponseBean deleteProduct(Long productId);
}
