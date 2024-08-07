package com.example.aecs.service;

import com.example.aecs.domain.dto.FileDTO;
import com.example.aecs.domain.dto.InquiryDTO;
import com.example.aecs.domain.dto.InquiryDetailDTO;
import com.example.aecs.domain.dto.InquiryListDTO;
import com.example.aecs.domain.oauth.CustomOAuth2User;
import com.example.aecs.domain.util.PagedResponse;
import com.example.aecs.domain.vo.FileVO;
import com.example.aecs.domain.vo.InquiryVO;
import com.example.aecs.mapper.FileMapper;
import com.example.aecs.mapper.InquiryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryMapper inquiryMapper;
    private final FileMapper fileMapper;

    @Override
    public List<InquiryListDTO> getInquiryList(int page, int pageSize) {

        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        return inquiryMapper.selectAll(startRow, endRow);
    }

    @Override
    public int getInquiryListCount() {
        return inquiryMapper.countInquiry();
    }

    @Override
    public void saveInquiry(InquiryDTO inquiry, List<MultipartFile> files) {
        long inquiryId = inquiryMapper.getSeq();
        inquiry.setInquiryId((int) inquiryId);
        inquiryMapper.saveInquiry(inquiry);  // 게시글 정보 저장

        saveFile((int) inquiryId, files);
    }

    @Override
    public InquiryDetailDTO getInquiryById(int inquiryId, CustomOAuth2User customOAuth2User) {
        InquiryDetailDTO inquiry = inquiryMapper.selectInquiryDetail(inquiryId);

        // 조회 수 상승을 결정할 if
        if (customOAuth2User == null || !customOAuth2User.getProviderId().equals(inquiry.getProviderId())) {
            // 조회 수가 플러스 1이 되는 update 쿼리문
            inquiryMapper.plusView(inquiryId);
        }

        // 날짜 필드를 문자열로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        inquiry.setInquiryRegisterDate(LocalDateTime.parse(inquiry.getInquiryRegisterDate(), formatter).format(formatter));
        inquiry.setInquiryUpdateDate(LocalDateTime.parse(inquiry.getInquiryUpdateDate(), formatter).format(formatter));

        return inquiry;
    }

    @Override
    public InquiryDetailDTO goUpdateInquiry(int inquiryId) {
        return inquiryMapper.selectInquiryDetail(inquiryId);
    }

    @Override
    public void updateInquiry(InquiryDTO inquiry, List<MultipartFile> files) {
        inquiryMapper.updateInquiry(InquiryVO.toentity(inquiry));
        fileMapper.deleteFile((long) inquiry.getInquiryId());

        saveFile((int) inquiry.getInquiryId(), files);
    }

    @Override
    public void saveFile(int inquiryId, List<MultipartFile> files) {
        // 현재 날짜를 기반으로 폴더 경로 생성
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String datePath = now.format(formatter);

        for (MultipartFile file : files) {
            // 방어코드
            if (file.isEmpty()) continue; // 파일이 비어있으면 건너뜀

            String originalFileName = file.getOriginalFilename();
            String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName;
            Long fileSize = file.getSize();

            try {
                // 파일 저장 경로 설정
                Path directoryPath = Paths.get("C:/upload/" + datePath);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath); // 폴더가 없으면 생성
                }
                Path filePath = directoryPath.resolve(storedFileName);
                // 파일 저장
                Files.copy(file.getInputStream(), filePath);

                FileDTO fileDTO = new FileDTO();
                fileDTO.setInquiryId(inquiryId);
                fileDTO.setOriginalFileName(originalFileName);
                fileDTO.setStoredFileName(directoryPath + "/" + storedFileName);
                fileDTO.setFileSize(Math.toIntExact(fileSize));

                fileMapper.insertFile(FileVO.toEntity(fileDTO)); // 파일 정보 저장

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteInquiry(int inquiryId) {
        inquiryMapper.deleteInquiry(inquiryId);
    }

    @Override
    public PagedResponse<InquiryListDTO> selectAllByDateDESC(int page, int pageSize) {
        return null;
    }

    @Override
    public PagedResponse<InquiryListDTO> selectAllByDateASC(int page, int pageSize) {
        return null;
    }

    @Override
    public PagedResponse<InquiryListDTO> selectAllByViews(int page, int pageSize) {
        return null;
    }

    @Override
    public PagedResponse<InquiryListDTO> selectD(int page, int pageSize, String sort, String searchType, String search) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalBoards = inquiryMapper.countDInquiry(searchType, search);
        int totalPages = (int) Math.ceil((double)totalBoards/pageSize);

        List<InquiryListDTO> boards = inquiryMapper.selectD(startRow, endRow, sort, searchType, search);

        return new PagedResponse<>(boards, page, totalPages, pageSize, totalBoards);

    }

    @Override
    public boolean validateRequestId(String requestId, String providerId) {
        return inquiryMapper.existsRequestId(requestId, providerId);
    }

}
