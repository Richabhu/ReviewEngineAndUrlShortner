package com.spring.product.review.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductReview {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(columnDefinition = "serial")
    protected Integer id;

    @Min(1)
    @Max(5)
    private double reviewScore;

    private String comment;

    private Integer userId;

    @Column(name = "product_id")
    private Integer productId;



}
