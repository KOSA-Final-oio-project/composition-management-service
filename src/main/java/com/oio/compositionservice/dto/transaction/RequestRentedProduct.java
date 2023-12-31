package com.oio.compositionservice.dto.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestRentedProduct {
    private Long rentedProductNo;

    private Long productNo;

    private String ownerNickname;

    private String borrowerNickname;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="Asia/Seoul")
    private LocalDateTime rentStartDate;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="Asia/Seoul")
    private LocalDateTime rentEndDate;
}
