package com.oio.compositionservice.client;

import com.oio.compositionservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    /*
    sort : 정렬기준 ( 조회수, 대여수 )
    order : 정렬방법( asc, desc )
    limit : 갯수
     */
    @GetMapping("/product-service/product")
    List<ProductDto> getProductOrderByRent(
            @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("limit") int limit);

    @GetMapping("/product-service/product")
    List<ProductDto> getProductOrderByView(
            @RequestParam("sort") String sort, @RequestParam("order") String order, @RequestParam("limit") int limit);

//    List<ProductDto> getProductOrderByDate(@RequestParam("sort") String date, @RequestParam("order") String desc, @RequestParam("limit") int limit);
}
