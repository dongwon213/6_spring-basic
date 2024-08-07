package com.example.aecs.mapper;

import com.example.aecs.domain.dto.InquiryDTO;
import com.example.aecs.domain.dto.InquiryDetailDTO;
import com.example.aecs.domain.dto.InquiryListDTO;
import com.example.aecs.domain.vo.InquiryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InquiryMapper {

    // 게시판 목록
    List<InquiryListDTO> selectAll(int startRow, int endRow);

    // 게시판 총 갯수
    // 페이징 처리리할 때 사용할 쿼리
    int countInquiry();

    // 다음 시퀀스 가져오기
    // 게시글 작성할 때 사용할 쿼리
    long getSeq();

    // 게시글 작성
    void saveInquiry(InquiryDTO inquiry);

    // 게시글 상세ㅗ기
    InquiryDetailDTO selectInquiryDetail(int inquiryId);

    // 조회수 +1
    void plusView(int inquiryId);

    // 개시글 수정하기
    void updateInquiry(InquiryVO inquiryVO);

    // 게시글 삭제하기
    void deleteInquiry(int inquiryId);

    // 게시글 오래된 순
    List<InquiryListDTO> selectAllByDateASC(int startRow, int endRow);

    // 게시글 조회순
    List<InquiryListDTO> selectAllByViews(int startRow, int endRow);

    // 동적쿼리
    List<InquiryListDTO> selectD(int startRow, int endRow, String sort, String searchType, String search);

    // 동적 쿼리 시, 게시글 갯수
    int countDInquiry(String searchType, String search);

    // requestId 존재 확인
    boolean existsRequestId(@Param("requestId") String requestId, @Param("providerId") String providerId);



}
