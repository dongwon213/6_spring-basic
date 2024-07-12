package com.example.board.service;

import com.example.board.domain.dto.FileDTO;
import com.example.board.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;

    @Override
    public List<FileDTO> getFIleListByBoardId(Long boardId) {
        return fileMapper.selectFileList(boardId);
    }

    @Override
    public FileDTO getFIleById(Long fileId) {
        return fileMapper.getFileById(fileId);
    }
}
