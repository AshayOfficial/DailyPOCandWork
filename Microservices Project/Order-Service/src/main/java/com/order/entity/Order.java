package com.order.entity;

import com.order.constants.OrderConstants;
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
import java.util.List;

@Entity
@Table(name = OrderConstants.TABLE_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotAudited
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "order_price")
    private Double orderPrice;
    @Column(name = "order_product_list")
    private List<String> orderProductList;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @NotAudited
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    @LastModifiedDate
    @NotAudited
    private LocalDateTime updatedDate;
    @Column(name = "is_order_cancelled")
    private Boolean isOrderCancelled = Boolean.FALSE;
}
