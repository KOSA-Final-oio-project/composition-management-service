package com.oio.compositionservice.dto.product;

import lombok.*;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Category {
    private Long categoryNo;

    private String categoryName;
}
