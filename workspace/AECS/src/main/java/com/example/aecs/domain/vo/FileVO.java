package com.example.aecs.domain.vo;

import com.example.aecs.domain.dto.FileDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class FileVO {
    private int fileId;
    private int inquiryId;
    private String originalFileName;
    private String storedFileName;
    private int fileSize;
    private String uploadTime;


    @Builder
    public FileVO(int fileId, int inquiryId, String originalFileName, String storedFileName, int fileSize, String uploadTime) {
        this.fileId = fileId;
        this.inquiryId = inquiryId;
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.fileSize = fileSize;
        this.uploadTime = uploadTime;
    }

    public static FileVO toEntity(FileDTO filesDTO) {
        return  FileVO.builder().fileId(filesDTO.getFileId())
                .inquiryId(filesDTO.getInquiryId())
                .originalFileName(filesDTO.getOriginalFileName())
                .storedFileName(filesDTO.getStoredFileName())
                .fileSize(filesDTO.getFileSize())
                .uploadTime(filesDTO.getUploadTime())
                .build();
    }



}
