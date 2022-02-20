package com.spring.product.review.responses;

import com.spring.product.review.models.CatalogProduct;
import com.spring.product.review.models.ProductReview;
import com.spring.product.review.responses.common.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductReviewResponse extends BaseResponse {
    private ProductReview productReview;
}