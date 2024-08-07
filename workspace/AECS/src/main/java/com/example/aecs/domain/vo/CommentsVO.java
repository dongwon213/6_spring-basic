package com.example.aecs.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class CommentsVO {
    private int commentId;
    private int inquiryId;
    private String providerId;
    private String commentContent;
    private String commentRegisterDate;
    private String commentUpdateDate;

}
