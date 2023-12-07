package com.oio.compositionservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDto {

    private String category;
    private String content;
    private String reporterNickname;
    private String reportedNickname;

}
