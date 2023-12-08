package com.oio.compositionservice.dto.product;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private Long addressNo;
    private String siDo;
    private String siGunGu;
    private String eupMyeonRo;

}
