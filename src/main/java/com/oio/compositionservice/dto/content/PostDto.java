package com.oio.compositionservice.dto.content;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDto {

    private Long pno;

    private String nickName;

    private String title;

    private String content;

    private String category;

    private int status;

    private int key;

    private Long password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    private List<String> fileNames;

}
