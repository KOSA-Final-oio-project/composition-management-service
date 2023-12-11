package com.oio.compositionservice.dto.content;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResponsePostModify {

    private String title;
    private String content;
}
