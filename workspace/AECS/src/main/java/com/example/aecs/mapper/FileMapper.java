package com.example.aecs.mapper;

import com.example.aecs.domain.dto.FileDTO;
import com.example.aecs.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    // 첨부 파일 insert
    // 첨부파일은 게시글의 insert 될 떄 날아가면 된다
    void insertFile(FileVO fileVO);

    List<FileDTO> selectFileList(Long boardId);

    // 첨부파일 DB에서 삭제
    // 게시글 업데이트 할 때 예정
    void deleteFile(Long boardId);

    // 첨부파일 가져오기
    // 다운로드 할 때 사용할 예정
    FileDTO getFileById(Long fileId);


}
