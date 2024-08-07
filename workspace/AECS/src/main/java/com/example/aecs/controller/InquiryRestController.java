package com.example.aecs.controller;

import com.example.aecs.domain.dto.InquiryListDTO;
import com.example.aecs.domain.util.PagedResponse;
import com.example.aecs.service.InquiryService;
import com.example.aecs.domain.oauth.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class InquiryRestController {

    private final InquiryService inquiryService;

    @GetMapping
    public ResponseEntity<PagedResponse<InquiryListDTO>> getBoardList(@RequestParam(defaultValue = "1") int page,
                                                                      @RequestParam(defaultValue = "10") int size,
                                                                      @RequestParam(defaultValue = "") String sort,
                                                                      @RequestParam(value = "searchType") String searchType,
                                                                      @RequestParam(value = "search") String search) {

        return ResponseEntity.ok(inquiryService.selectD(page, size, sort, searchType, search));
    }

    @GetMapping("/validateRequestId")
    public ResponseEntity<Boolean> validateRequestId(@RequestParam String requestId, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        boolean isValid = inquiryService.validateRequestId(requestId, customOAuth2User.getProviderId());
        return ResponseEntity.ok(isValid);
    }
}
