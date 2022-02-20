package com.spring.product.review.controller;



import com.spring.product.review.models.Customer;
import com.spring.product.review.responses.CustomerResponse;
import com.spring.product.review.responses.common.StatusResponse;
import com.spring.product.review.responses.common.enumeration.SuccessCodes;
import com.spring.product.review.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer/")
public class CustomerController {

    @Autowired
    CustomerService userService;

    protected CustomerResponse createResponse(Customer users) {
        CustomerResponse response = new CustomerResponse();
        response.setUsers(users);
        return response;
    }

    @RequestMapping(
            value = {"create"},
            method = {RequestMethod.POST}
    )
    public CustomerResponse create(@RequestBody Customer input) throws Exception {

        CustomerResponse response = this.createResponse(null);

        Customer result = userService.create(input);

        response = this.createResponse(result);
        response.setStatus(new StatusResponse(SuccessCodes.OK, 1));
        return response;

    }
}
