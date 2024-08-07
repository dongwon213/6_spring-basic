package com.example.aecs.service;

import com.example.aecs.domain.dto.FileDTO;
import com.example.aecs.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {


    private final FileMapper fileMapper;



    @Override
    public List<FileDTO> getFIleListByInquiryId(int inquiryId) {
        return fileMapper.selectFileList((long) inquiryId);
    }

    @Override
    public FileDTO getFIleById(int fileId) {
        return fileMapper.getFileById((long) fileId);
    }
}
