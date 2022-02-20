package com.spring.product.review.urlService;

import com.spring.product.review.URLRepository.UrlRepository;
import com.spring.product.review.urlModel.UrlShortner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sun.tools.tree.BooleanExpression;

import java.util.Base64;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.springframework.web.client.HttpClientErrorException.*;

@Service
public class URLService {

    public static Set<String> shortCodes;
    public static String shortURlPrefix;

    URLService() {
        shortCodes = new HashSet<>();
        shortURlPrefix = "www.tinyUrl.com/";
    }

    @Autowired
    UrlRepository urlRepository;

    public UrlShortner createUrl(UrlShortner url){

        UrlShortner existingUrlData = fetchExistingUrl(url);
        if(existingUrlData != null)
            return existingUrlData;

        int codeLength = 6;
        String shortUrl = generateShortUrl(url.getLongUrl(), codeLength);

        if(checkUniqueCode(shortUrl))
        {
            url.setShortUrl(shortURlPrefix+shortUrl);
            urlRepository.save(url);
            return url;
        }
        else{
            // already generated Code
            codeLength = 7;
            while(codeLength <= 8)
            {
                shortUrl = generateShortUrl(url.getLongUrl(), codeLength);
                if(checkUniqueCode(shortUrl))
                {
                    url.setShortUrl(shortURlPrefix+shortUrl);
                    break;
                }
                codeLength++;

            }
            if(codeLength == 9)
            {
                // generate url with unique character insertion
                url.setShortUrl(shortURlPrefix + modifyShortUrl(shortUrl));
            }

        }
        urlRepository.save(url);
        return url;

    }

    public String generateShortUrl(String url, int characterNo){
        String encodedUrl =  Base64.getUrlEncoder().encodeToString(url.getBytes());
        System.out.println(encodedUrl);
        System.out.println(encodedUrl.getBytes());

        String shortenedUrl = encodedUrl.substring(encodedUrl.length() - characterNo );
        return shortenedUrl;

    }

    public boolean checkUniqueCode(String shortUrl){
        if(shortCodes.contains(shortUrl))
            return false;
        shortCodes.add(shortUrl);
        return true;
    }

    public String modifyShortUrl(String url)
    {
        // qwertyv
        int startChar = 1;
        int charIndex = 1;
        String modifiedUrl = url.substring(0, url.length() - charIndex) +
                (char)((int)url.charAt(url.length() + startChar));
        while(shortCodes.contains(modifiedUrl))
        {
            // todo: handle it for other characters as well, when character max limit reached exception is thrown
            startChar++;
            modifiedUrl = url.substring(0, url.length() - charIndex) +
                    (char)((int)url.charAt(url.length() + startChar));
        }
        return modifiedUrl;
    }


    public UrlShortner fetchExistingUrl(UrlShortner url){

        Optional<UrlShortner> urlPresent = urlRepository.findByUserIdAndLongUrl(url.getUserId(), url.getLongUrl());
        if(urlPresent.isPresent())
        {
            return urlPresent.get();
        }
        return null;
    }


    public UrlShortner fetchLongUrl(UrlShortner url){
        Optional<UrlShortner> urlPresent = urlRepository.findByUserIdAndShortUrl(url.getUserId(), url.getShortUrl());

        if(urlPresent.isPresent())
        {
            return urlPresent.get();
        }
        // invalid short url
        // invalid user id
        String shortURlSuffix = url.getShortUrl().substring(shortURlPrefix.length());
        System.out.println("Short Url : " + shortURlSuffix);
//        if(!shortCodes.contains(shortURlSuffix))
//            throw new BadRequest("Invalid Short Code f");
        return null;
    }

}
