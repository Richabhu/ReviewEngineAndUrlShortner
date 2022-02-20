package com.spring.product.review.URLRepository;

import com.spring.product.review.urlModel.UrlShortner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlShortner, Integer> {

    Optional<UrlShortner> findByUserIdAndLongUrl(int userId, String longUrl);

    Optional<UrlShortner> findByUserIdAndShortUrl(int userId, String shortUrl);
}
