package com.spring.product.review.controller;

import com.spring.product.review.models.ProductReview;
import com.spring.product.review.responses.ProductReviewResponse;
import com.spring.product.review.responses.common.StatusResponse;
import com.spring.product.review.responses.common.enumeration.ErrorCodes;
import com.spring.product.review.responses.common.enumeration.SuccessCodes;
import com.spring.product.review.services.ProductReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestController
@RequestMapping("/api/v1/review/")
public class ProductReviewController {

    @Autowired
    ProductReviewService productReviewService;

    protected ProductReviewResponse createResponse(ProductReview productReview) {
        ProductReviewResponse response = new ProductReviewResponse();
        response.setProductReview(productReview);
        return response;
    }

    @RequestMapping(
            value = {""},
            method = {RequestMethod.POST}
    )
    public ProductReviewResponse create(@RequestBody ProductReview input) throws Exception {

        ProductReviewResponse response = this.createResponse(null);

        try {
            ProductReview result = productReviewService.addReview(input);

            response = this.createResponse(result);
            response.setStatus(new StatusResponse(SuccessCodes.OK, 1));
            return response;
        }
        catch (EntityNotFoundException dataNotFound){
            log.error("Error occurred: {}", dataNotFound.getMessage());
            response.setStatus(new StatusResponse(ErrorCodes.NOT_FOUND, 0));
            return response;

        }

    }
}
