package com.example.board.domain.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


// list.html에 뿌려줄 데이터를 담아주는 DTO
@Component
@Data
public class BoardListDTO {

    // PK
    private Long boardId;
    // 제목
    private String boardTitle;
    // 작성자
    private String name;
    // 내용
    private String boardContent;
    // 조회 수
    private int boardViews;
    // 작성 일
    private LocalDateTime boardRegisterDate;
    // 수정 일
    private LocalDateTime boardUpdateDate;
    // 첨부파일의 수
    private int fileCount;
    // 작성자 PK
    private String providerId;

}