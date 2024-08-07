package com.example.board.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class NoticeDTO {
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime noticeDate;


}
