package com.product.controller;

import com.product.beans.request.ProductRequestBean;
import com.product.beans.response.CommonResponseBean;
import com.product.constants.ProductConstants;
import com.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ProductConstants.REQUEST_MAPPING_URL)
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<CommonResponseBean> getAllProducts() {
        return ResponseEntity.ok(this.productService.getAllProducts());
    }

    @GetMapping("/product-id")
    public  ResponseEntity<CommonResponseBean> getProductById(@PathVariable(name = "product-id") Long productId) {
        log.info("INSIDE GET BY ID API CONTROLLER");
        return ResponseEntity.ok(this.productService.getProductById(productId));
    }

    @PostMapping
    public ResponseEntity<CommonResponseBean> addProduct(@RequestBody ProductRequestBean productRequestBean) {
        return ResponseEntity.ok(this.productService.addProduct(productRequestBean));
    }

    @PutMapping
    public ResponseEntity<CommonResponseBean> updateProduct(@RequestBody ProductRequestBean productRequestBean) {
        return ResponseEntity.ok(this.productService.updateProduct(productRequestBean));
    }

    @DeleteMapping("/product-id")
    public ResponseEntity<CommonResponseBean> deleteProduct(@PathVariable(name = "product-id") Long productId) {
        return ResponseEntity.ok(this.productService.deleteProduct(productId));
    }
}
