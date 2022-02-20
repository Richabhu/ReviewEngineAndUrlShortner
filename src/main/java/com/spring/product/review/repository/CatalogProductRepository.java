package com.spring.product.review.repository;

import com.spring.product.review.models.CatalogProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogProductRepository extends JpaRepository<CatalogProduct, Integer> {
}
