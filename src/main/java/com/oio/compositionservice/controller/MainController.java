package com.oio.compositionservice.controller;

import com.oio.compositionservice.client.ProductServiceClient;
import com.oio.compositionservice.dto.product.ProductListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oio")
public class MainController {

    private final ProductServiceClient productServiceClient;

    @GetMapping("/")
    public Map<String,Map<Object,Object>> getMainInformation(ProductListRequest productListRequest){
        Map result = new HashMap();
        Map resultByRental = productServiceClient.productList("r",productListRequest);
        Map resultByView = productServiceClient.productList("v",productListRequest);
        Map resultByWhole = productServiceClient.productList("n",productListRequest);

        result.put("resultByRental",resultByRental);
        result.put("resultByView",resultByView);
        result.put("resultByWhole",resultByWhole);

        return result;
    }



}
