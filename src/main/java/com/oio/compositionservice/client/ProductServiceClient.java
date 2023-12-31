package com.oio.compositionservice.client;

import com.oio.compositionservice.dto.product.CategoryDto;
import com.oio.compositionservice.dto.product.Product;
import com.oio.compositionservice.dto.product.ProductDto;
import com.oio.compositionservice.dto.product.ProductListRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

@FeignClient(name = "product-service")
public interface ProductServiceClient {


    @PostMapping(value = "/product/writeProduct/{addressNo}/{categoryName}/{nickname}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> createProduct(@PathVariable String categoryName,
                                         @PathVariable Long addressNo,
                                         @PathVariable String nickname,
                                         @RequestParam Map<String, Object> product,
                                         @RequestPart List<MultipartFile> files);

    @PutMapping("/product/modifyProduct/{productNo}")
    String modifyProduct(@PathVariable Long productNo, @RequestBody ProductDto product);

    @GetMapping(value = "/product/productDetail/{productNo}/{nickname}")
    Map<String, Object> productDetail(@PathVariable Long productNo, @PathVariable String nickname);

    @GetMapping("/product/productList/{type}")
    Map productList(@PathVariable String type,@ModelAttribute ProductListRequest productListRequest);

    @GetMapping("/product/myProduct/{nickname}/{postCategory}")
    List<Product> myProduct(@PathVariable Integer postCategory, @PathVariable String nickname);

    @DeleteMapping("/product/delete/{productNo}")
    void deleteProductById(@PathVariable Long productNo);

    @GetMapping("/category/categoryList")
    List<String> listOfCategory();

    @PostMapping("/category/addCategory")
    void insertCategory(CategoryDto category);

    @GetMapping("/address/siDoList")
    List<String> siDoList();

    @GetMapping("/address/siGunGuList/{siDo}")
    List<String> siGunGuList(@PathVariable String siDo);

    @GetMapping("/address/eupMyeonRoList/{siDo}/{siGunGu}")
    List<String> eupMyeonRoList(@PathVariable String siDo, @PathVariable String siGunGu);
}
