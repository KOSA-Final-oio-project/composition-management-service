package com.oio.compositionservice.client;

import com.oio.compositionservice.dto.RequestRentedProduct;
import com.oio.compositionservice.dto.RequestReview;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "transaction-service")
public interface TransactionServiceClient {

    //대여

    //대여 관련 물품 조회
    @GetMapping("rent/{status}")
    public String getRentedProduct(@RequestParam String nickname, @PathVariable Long status);

    //대여 시작
    @PostMapping ("rent/{productNo}")
    String startRent(@PathVariable("productNo") Long productNo, @RequestBody RequestRentedProduct requestRentedProduct);

    //대여 완료
    @PutMapping("/rent/{rentedProductNo}")
    String updateRentStatus(@PathVariable("rentedProductNo") Long rentedProductNo);


    //리뷰

    //상품 번호로 리뷰 전체 조회
    @GetMapping("reviews/{productNo}")
    String getProductReview(@PathVariable("productNo") Long productNo);

    //리뷰 좋아요
    @PutMapping("{reviewNo}")
    void updateHeart(@PathVariable Long reviewNo);


    //리뷰 삭제
    @DeleteMapping("{reviewNo}")
    void deleteReview(@PathVariable Long reviewNo);

    //리뷰 작성
    @PostMapping("{productNo}/{rentedProductNo}")
    String createReview(@PathVariable Long rentedProductNo, @PathVariable Long productNo, RequestReview requestReview);

    @GetMapping("{reviewNo}")
    String getReviewDetail(@PathVariable Long reviewNo);

    @GetMapping("/myreviews/{status}")
    String getMyReview(@PathVariable String nickname, @PathVariable Long status);
}
