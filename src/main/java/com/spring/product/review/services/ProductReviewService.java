package com.spring.product.review.services;

import com.spring.product.review.models.CatalogProduct;
import com.spring.product.review.models.ProductReview;
import com.spring.product.review.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductReviewService {

    @Autowired
    ProductReviewRepository productReviewRepository;

    @Autowired
    CatalogProductService catalogProductService;

    public ProductReview addReview(ProductReview productReview) throws Exception {
        // fetch the data from product catalog
        CatalogProduct productDetails = catalogProductService.getProductById(productReview.getProductId());
        if(productDetails!=null)
        {
            //valid product
            ProductReview productReview1 = productReviewRepository.save(productReview);
            // now update productDetails [compute aggregated score]
            int noOfReviews = productDetails.getProductReviews().size();
            if(noOfReviews > 0)
            {
                productDetails.getProductReviews().add(productReview1);
                double aggregatedScore = (productDetails.getAggregatedScore() * noOfReviews + productReview.getReviewScore())/(noOfReviews + 1);
                productDetails.setAggregatedScore(aggregatedScore);
            }
            else
            {
                // first review
                List<ProductReview> productReviewList = new ArrayList<ProductReview>(Arrays.asList(productReview1));
                productDetails.setProductReviews(productReviewList);
                productDetails.setAggregatedScore(productReview.getReviewScore());
            }
            // update product Details
            catalogProductService.update(productDetails);
        }
        else
        {
            //product not valid
            throw new EntityNotFoundException("Item Not found");
        }
        return productReview;
    }
}
