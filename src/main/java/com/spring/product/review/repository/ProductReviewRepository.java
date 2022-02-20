package com.spring.product.review.repository;

import com.spring.product.review.models.CatalogProduct;
import com.spring.product.review.models.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {
}
