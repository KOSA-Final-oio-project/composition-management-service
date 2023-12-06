package com.oio.compositionservice.controller;

import com.oio.compositionservice.client.ProductServiceClient;
import com.oio.compositionservice.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oio")
public class HomeController {

    private final ProductServiceClient productServiceClient;

    //섹션1
    @RequestMapping("/")
    public ResponseEntity<Map<String,List>> getProductOrderByRent() {
        Map map = new HashMap();
        //최다 대여수를 기준으로 정렬한 상품 top3
        List<ProductDto> orderByRent = productServiceClient.getProductOrderByRent(
             "rentalCount","desc",3);
        map.put("orderByRent", orderByRent);

        //최다 조회수를 기준으로 정렬한 상품 top3
        List<ProductDto> orderByView = productServiceClient.getProductOrderByView(
                "viewCount" , "desc",  3);
        map.put("orderByView", orderByView);

        //등록일 기준으로 정렬한 전체 상품
//        List<ProductDto> orderByDate = productServiceClient.getProductOrderByDate(
//                "date","desc",50);
//        map.put("orderByDate", orderByDate);

     return ResponseEntity.status(HttpStatus.OK).body(map);
    }




    //섹션2
    //최신순으로 상품 정렬 (무한스크롤)


}
