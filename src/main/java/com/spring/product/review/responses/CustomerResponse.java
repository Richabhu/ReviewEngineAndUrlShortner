package com.spring.product.review.responses;

import com.spring.product.review.models.Customer;
import com.spring.product.review.responses.common.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerResponse extends BaseResponse {
    private Customer users;
}