package com.example.aecs.controller;

import com.example.aecs.domain.dto.FileDTO;
import com.example.aecs.service.FileService;
import com.nimbusds.jose.util.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@RestController
@RequestMapping("/download")
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;

    @GetMapping("{fileId}")
    public ResponseEntity<Resource> download(@PathVariable("fileId") int fileId) {
        FileDTO file = fileService.getFIleById(fileId);

        // 실제 파일의 경로를 생성
        Path filePath = Path.of(file.getStoredFileName()); // 실제 파일이 저장되어 있는 경로

        // 해당 경로의 파일을 resource 경로로 로드
        org.springframework.core.io.Resource resource = new FileSystemResource(filePath); // 해당 결로로 들어가 파일 가져오기

        // 리소스가 존재하는지 확인하고, 존재하지 않으면 404 에러반환
        if(!resource.exists()){
            return ResponseEntity.notFound().build();
        }

        // 원본 파일 이름을 Context.Disposition 헤더에서 사용할 수 있게끔 인코딩.
        // 파일 이름을 URL로 인코딩한다
        // 이는 파일 이름에 공백이나 특수문자가 포함되어 있을 경우, 문제가 발생하지 않도록 하기 위함
        String encodedFileName = URLEncoder.encode(file.getOriginalFileName()
                , StandardCharsets.UTF_8 ).replace("+", "%20");

        // 클라이언트가 응답을 어떻게 처리할지 결정
        // attachment -> 파일을 다운로드 해라
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";


        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body((Resource) resource);
    }



}

