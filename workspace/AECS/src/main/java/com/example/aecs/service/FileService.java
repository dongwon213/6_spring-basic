package com.example.aecs.service;

import com.example.aecs.domain.dto.FileDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {

    List<FileDTO> getFIleListByInquiryId(int boardId);

    FileDTO getFIleById(int fileId);

}
