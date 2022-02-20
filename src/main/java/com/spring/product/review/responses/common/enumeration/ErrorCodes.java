package com.spring.product.review.responses.common.enumeration;

import com.spring.product.review.responses.common.StatusCode;

public enum ErrorCodes implements StatusCode {
    GENERIC_ERROR_OCCURRED(101, "Error Occurred!"),
    NOT_FOUND(102, "Data not found"),
    BAD_REQUEST(400, "Internal Server Error"),
    ITEM_NOT_FOUND(400, "Item not found!"),
    REVIEW_SCORE_VALIDATION(400, "Review Score should be between 1 to 5.");

    Integer code;
    String message;

    private ErrorCodes(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
