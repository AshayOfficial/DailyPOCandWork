package com.product.entity;

import com.product.constants.ProductConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = ProductConstants.TABLE_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
public class Product {
    public static final String TABLE_NAME = "Product";
    @Id
    @NotAudited
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private String productPrice;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "product_stock_quantity")
    private Long productStockQuantity;
    @CreatedDate
    @NotAudited
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @NotAudited
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(name = "is_active_status")
    private Boolean activeStatus;
}
