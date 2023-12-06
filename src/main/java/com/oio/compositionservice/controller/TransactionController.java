package com.oio.compositionservice.controller;

import com.oio.compositionservice.client.TransactionServiceClient;
import com.oio.compositionservice.dto.RequestRentedProduct;
import com.oio.compositionservice.dto.RequestReview;
import com.oio.compositionservice.module.Decoder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oio")
public class TransactionController {

    private final TransactionServiceClient transactionServiceClient;
    private final Decoder decoder;

    @GetMapping("/{status}")
    public String getRentedProduct(HttpServletRequest request, @PathVariable Long status){
        String username = decoder.decode(request);
        String rentedProduct = transactionServiceClient.getRentedProduct(username, status);
        return rentedProduct;
    }

    @PostMapping("/{productNo}")
    public String startRent(@PathVariable("productNo") Long productNo, @RequestBody RequestRentedProduct requestRentedProduct){
        String result = transactionServiceClient.startRent(productNo, requestRentedProduct);
        return result;
    }

    @PutMapping("/{rentedProductNo}")
    String updateRentStatus(@PathVariable("rentedProductNo") Long rentedProductNo){
        String result = transactionServiceClient.updateRentStatus(rentedProductNo);
        return result;
    }


    //리뷰

    //리뷰 작성
    @PostMapping("/{productNo}/{rentedProductNo}")
    public String createReview(@PathVariable("rentedProductNo") Long rentedProductNo,
                                                       @PathVariable("productNo") Long productNo, @RequestBody RequestReview requestReview) {
        String result = transactionServiceClient.createReview(rentedProductNo,productNo,requestReview);
        return result;
    }

    //상품 번호로 해당 상품 리뷰 전체 조회
    @GetMapping("/reviews/{productNo}")
    public String getProductReview(@PathVariable("productNo") Long productNo){
        String reviews = transactionServiceClient.getProductReview(productNo);
        return reviews;
    }

    //리뷰 번호로 리뷰 상세 조회
    @GetMapping("/{reviewNo}")
    public String getReviewDetail(@PathVariable("reviewNo") Long reviewNo){
        String result = transactionServiceClient.getReviewDetail(reviewNo);
        return result;
    }

    //사용자 리뷰 조회(status 0: 작성한 리뷰 , 1 = 받은 리뷰)
    @GetMapping("/myreviews/{status}")
    public String getMyReview(@RequestParam String nickname, @PathVariable Long status) {
        String result = transactionServiceClient.getMyReview(nickname,status);
        return result;
    }

    //리뷰 좋아요
    @PutMapping("/{reviewNo}")
    public void updateHeart(@PathVariable Long reviewNo){
        transactionServiceClient.updateHeart(reviewNo);
    }

    //리뷰 삭제
    @DeleteMapping("/{reviewNo}")
    public void deleteReview(@PathVariable("reviewNo") Long reviewNo){
        transactionServiceClient.deleteReview(reviewNo);
    }

}
