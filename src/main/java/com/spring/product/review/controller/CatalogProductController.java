package com.spring.product.review.controller;

import com.spring.product.review.models.CatalogProduct;
import com.spring.product.review.responses.CatalogProductResponse;
import com.spring.product.review.responses.common.StatusCode;
import com.spring.product.review.responses.common.StatusResponse;
import com.spring.product.review.responses.common.enumeration.ErrorCodes;
import com.spring.product.review.responses.common.enumeration.StatusType;
import com.spring.product.review.responses.common.enumeration.SuccessCodes;
import com.spring.product.review.services.CatalogProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;


@Slf4j
@RestController
@RequestMapping("/api/v1/product/")
public class CatalogProductController {

    @Autowired
    private CatalogProductService catalogProductService;

    protected CatalogProductResponse createResponse(CatalogProduct catalogProduct) {
        CatalogProductResponse response = new CatalogProductResponse();
        response.setCatalogProduct(catalogProduct);
        return response;
    }

    @RequestMapping(
            value = {"create"},
            method = {RequestMethod.POST}
    )
    public CatalogProductResponse create(@RequestBody CatalogProduct input) throws Exception {

        CatalogProductResponse response = this.createResponse(null);

        CatalogProduct result = catalogProductService.createProduct(input);

        response = this.createResponse(result);
        response.setStatus(new StatusResponse(SuccessCodes.OK, 1));
        return response;

    }

    @RequestMapping(
            value = {"get/{id}"},
            method = {RequestMethod.GET}
    )
    public CatalogProductResponse getItem(@PathVariable Integer id) throws Exception {

        CatalogProductResponse response = this.createResponse(null);


            CatalogProduct result = catalogProductService.getProductById(id);
            response = this.createResponse(result);
            response.setStatus(new StatusResponse(SuccessCodes.OK, 1));

        return response;
    }
}
