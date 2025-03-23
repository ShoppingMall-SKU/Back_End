package com.springboot.mealkart.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class CommonException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String serviceName;
    private final Object parameter;

    public CommonException(ErrorCode errorCode) {
        this(errorCode, null, null);
    }

    public CommonException(ErrorCode errorCode, String serviceName) {
        super(errorCode != null ? errorCode.getMessage() : "Unknown error");
        this.errorCode = errorCode;
        this.serviceName = serviceName;
        this.parameter = null;
    }

    public CommonException(ErrorCode errorCode, String serviceName, Object parameter) {
        super(errorCode != null ? errorCode.getMessage() : "Unknown error");
        this.errorCode = errorCode;
        this.serviceName = serviceName;
        this.parameter = parameter;
    }

    @Override
    public String getMessage() {
        String baseMessage = (errorCode != null) ? errorCode.getMessage() : "Unknown error";
        if (serviceName != null && parameter != null) {
            return String.format("[%s] %s | Parameter: %s", serviceName, baseMessage, parameter);
        } else if (serviceName != null) {
            return String.format("[%s] %s", serviceName, baseMessage);
        } else {
            return baseMessage;
        }
    }
}
