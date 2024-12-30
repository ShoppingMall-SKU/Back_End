package com.springboot.mealkart.exception;

import jakarta.validation.constraints.*;
import lombok.Getter;


@Getter
public class ExceptionDto{
    @Min(40000)
    private Integer code;
    @NotBlank
    private String message;

    public ExceptionDto(ErrorCode errorCode, String message) {
        this.code = errorCode.getCode();
        this.message = message;
    }
}
