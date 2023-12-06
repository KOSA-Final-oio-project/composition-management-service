package com.oio.compositionservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestReview {
    private Long rentedProductNo;

    private Long productNo;

    private String writerNickname;

    private String receiverNickname;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="Asia/Seoul")
    private LocalDateTime postDate;

    private String content;
}
