package com.order.system.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ItemDTO {
    private Long itemId;

    private String itemName;

    private float itemPrice;

    private Integer itemQuantity;

    private String shippedDate;
}
