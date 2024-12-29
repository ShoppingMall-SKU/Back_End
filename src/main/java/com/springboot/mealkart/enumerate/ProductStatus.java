package com.springboot.mealkart.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatus {
    STATUS_COLD("냉장"),
    STATUS_FROZEN("냉동"),
    STATUS_ROOM_TEMP("실온");

    private String status;
}
