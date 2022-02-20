package com.spring.product.review.urlController;

import com.spring.product.review.urlModel.UrlShortner;
import com.spring.product.review.urlService.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url/")
public class UrlController {


    @Autowired
    URLService urlService;

    @RequestMapping(
            value = {"create"},
            method = {RequestMethod.POST}
    )
    private UrlShortner saveUrl(@RequestBody UrlShortner url) {
        return urlService.createUrl(url);
    }


    //todo: convert into get and handle exceptions properly
    @RequestMapping(
            value = {"fetch"},
            method = {RequestMethod.GET}
    )
    private UrlShortner fetchUrl(@RequestBody UrlShortner url) {
        return urlService.fetchLongUrl(url);
    }
}
