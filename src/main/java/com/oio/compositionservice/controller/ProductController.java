package com.oio.compositionservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oio.compositionservice.client.ProductServiceClient;
import com.oio.compositionservice.client.TransactionServiceClient;
import com.oio.compositionservice.dto.product.CategoryDto;
import com.oio.compositionservice.dto.product.Product;
import com.oio.compositionservice.dto.product.ProductDto;
import com.oio.compositionservice.dto.product.ProductListRequest;
import com.oio.compositionservice.module.Decoder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oio")
public class ProductController {

    private final TransactionServiceClient transactionServiceClient;

    private final ProductServiceClient productServiceClient;

    private final ObjectMapper objectMapper;
    private final Decoder decoder;

    //상품 등록 test
    @PostMapping(value = "/product/{addressNo}/{categoryName}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> writeProduct(@PathVariable String categoryName,
                                               @PathVariable Long addressNo,
                                               @RequestPart("productDto") ProductDto product,
                                               List<MultipartFile> files,HttpServletRequest request) {
        String nickname = decoder.decode(request);
        MultipartFile mFile = null;
        List<MultipartFile> list = new ArrayList();
        for(MultipartFile file : files) {
            File temp = new File("C:/oioback", file.getOriginalFilename());

            try {
                file.transferTo(temp);//임시파일 생성
                FileItem fileItem =
                        new org.apache.commons.fileupload.disk.DiskFileItem(
                                "file",
                                Files.probeContentType(temp.toPath()),
                                false,
                                //temp.getName(),
                                file.getOriginalFilename(),
                                (int) temp.length(),
                                temp.getParentFile()
                        );


                InputStream input = new FileInputStream(temp);
                OutputStream output = fileItem.getOutputStream();
                IOUtils.copy(input, output);
                mFile = new CommonsMultipartFile(fileItem);
                /*-------------------*/
                input.close();
                output.close();
                list.add(mFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("asd");
            }

        }
        Map<String, Object> map = objectMapper.convertValue(product, new TypeReference<Map<String, Object>>() {
        });

        ResponseEntity<String> result = productServiceClient.createProduct(categoryName,addressNo,nickname,map,list);

        return result;
    }


    //상품 수정 test
    @PutMapping(value = "/product/{productNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String modifyProduct(@PathVariable Long productNo, @RequestBody ProductDto product){
        String result = productServiceClient.modifyProduct(productNo,product);
        return result;
    };

    //상품 상세정보 test
    @GetMapping(value = "/product-detail/{productNo}")
    public Map<String, Object> productDetail(@PathVariable Long productNo,HttpServletRequest request) {
        String decode = decoder.decode(request);
        Map map = new HashMap();
        String productReview = transactionServiceClient.getProductReview(productNo);
        Map<String, Object> stringObjectMap = productServiceClient.productDetail(productNo, decode);
        map.put("product",stringObjectMap);
        map.put("review",productReview);
        return map;
    }

    //상품 리스트 출력 test
//    @PostMapping("/productList")
//    public Map productList(@RequestBody ProductListRequest request) {
//        return productServiceClient.productList(request);
//    }

    //내가 등록한 상품 test
    @GetMapping(value = "/product/{postCategory}")
    public List<Product> myProduct(@PathVariable Integer postCategory,HttpServletRequest request) {
        String nickname = decoder.decode(request);
        return productServiceClient.myProduct(postCategory,nickname);
    }

    //상품 삭제 test
    @DeleteMapping("/product/{productNo}")
    public void deleteProduct(@PathVariable Long productNo) {
        productServiceClient.deleteProductById(productNo);
    }


    //카테고리

    //카테고리 불러오기 test
    @GetMapping("/category")
    public List<String> categoryList() {
        return productServiceClient.listOfCategory();
    }


    //카테고리 추가 test
    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto category) {
        productServiceClient.insertCategory(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //주소
    @GetMapping("/siDoList")
    public List<String> siDOList() {
        return productServiceClient.siDoList();
    }

    @GetMapping(value = "/siGunGuList/{siDo}")
    public List<String> siGunGuList(@PathVariable String siDo){
        return productServiceClient.siGunGuList(siDo);
    }

    @GetMapping(value = "/eupMyeonRoList/{siDo}/{siGunGu}")
    public List<String> eupMyeonRoList(@PathVariable String siDo, @PathVariable String siGunGu){
        return  productServiceClient.eupMyeonRoList(siDo, siGunGu);
    }

}
