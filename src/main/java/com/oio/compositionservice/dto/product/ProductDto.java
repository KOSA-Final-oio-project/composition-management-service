package com.oio.compositionservice.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.Date;

@Getter
public class ProductDto {
    public static final String NamedQuery_IncreaseViewCount = "increaseViewCount";

    private String nickname;

    private String title;

    private String content;

    private String priceCategory;

    private Integer price;
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "Asia/Seoul")
    private Date startDate;

    private Date endDate;

    private Integer postCategory;


}
