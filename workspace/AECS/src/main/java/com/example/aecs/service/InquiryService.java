package com.example.aecs.service;

import com.example.aecs.domain.dto.InquiryDTO;
import com.example.aecs.domain.dto.InquiryDetailDTO;
import com.example.aecs.domain.dto.InquiryListDTO;
import com.example.aecs.domain.oauth.CustomOAuth2User;
import com.example.aecs.domain.util.PagedResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface InquiryService {

    // 페이징 처리된 게시글 목록
    List<InquiryListDTO> getInquiryList(int page, int pageSize);

    // 게시판 총 갯수
    // 페이징 처리리할 때 사용할 쿼리
    int getInquiryListCount();

    // 게시글 작성
    // 첨부파일 insert
    void saveInquiry(InquiryDTO inquiry, List<MultipartFile> files) ;

    // 게시글 상세보기
    InquiryDetailDTO getInquiryById(int inquiryId, CustomOAuth2User customOAuth2User);

    // 게시글 업데이트로 이영할 때 가지고 갈 board select
    InquiryDetailDTO goUpdateInquiry(int inquiryId);



    // 게시글 업데이트
    // 수정하기를 누르면, 첨부파일은 초기화가 되게끔 구현
    void updateInquiry(InquiryDTO inquiry, List<MultipartFile> files);

    // 첨부파일 추가하는 메소드
    void saveFile(int inquiryId, List<MultipartFile> file);

    // 게시글 삭제
    void deleteInquiry(int inquiryId);

    // 최신순
    PagedResponse<InquiryListDTO> selectAllByDateDESC(int page, int pageSize);

    // 오래된 순
    PagedResponse<InquiryListDTO> selectAllByDateASC(int page, int pageSize);

    // 조회순
    PagedResponse<InquiryListDTO> selectAllByViews(int page, int pageSize);

    // 동적쿼리
    PagedResponse<InquiryListDTO> selectD(int page, int pageSize, String sort, String searchType, String search);

    // rest로 requestID확인
    boolean validateRequestId(String requestId, String providerId);



}
