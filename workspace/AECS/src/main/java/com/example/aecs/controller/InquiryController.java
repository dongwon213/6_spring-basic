package com.example.aecs.controller;

import com.example.aecs.domain.dto.*;
import com.example.aecs.domain.oauth.CustomOAuth2User;
import com.example.aecs.domain.vo.OwnerVO;
import com.example.aecs.mapper.OwnerMapper;
import com.example.aecs.service.FileService;
import com.example.aecs.service.InquiryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/inquiry")
@RequiredArgsConstructor
public class InquiryController {

    private final OwnerMapper ownerMapper;
    private final InquiryService inquiryService;
    private final FileService fileService;

    @GetMapping("/list")
    public String list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       Model model){

        int totalBoards = inquiryService.getInquiryListCount();
        int totalPages = (int) Math.ceil((double)totalBoards/pageSize);

        List<InquiryListDTO> boards = inquiryService.getInquiryList(pageNo, pageSize);

        int pageGroupSize = 5;
        int startPage = ((pageNo - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        model.addAttribute("boards", boards);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "list";

    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join")
    public String join(@RequestParam String ownerType,
                       @RequestParam String phoneNumber,
                       @RequestParam String address,
                       @RequestParam String licence,
                       @AuthenticationPrincipal CustomOAuth2User customOAuth2User){

        OwnerDTO ownerDTO = ownerMapper.findByProviderId(customOAuth2User.getProviderId());

        ownerDTO.setOwnerType(ownerType);
        ownerDTO.setRole("basic");
        ownerDTO.setPhoneNumber(phoneNumber);
        ownerDTO.setAddress(address);
        ownerDTO.setLicence(licence);

        ownerMapper.insertNewOwner(OwnerVO.toEntity(ownerDTO));

        return "redirect:/inquiry/list";
    }

    @GetMapping("login")
    public String goForm(HttpSession session){

        session.invalidate();

        return "loginForm";
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("inquiryDTO", new InquiryDTO());
        return "write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute InquiryDTO inquiryDTO,
                        @RequestParam("providerId") String providerId,
                        @RequestParam("inquiryFiles") List<MultipartFile> files){

        inquiryDTO.setProviderId(providerId);

        inquiryService.saveInquiry(inquiryDTO, files);

        return "redirect:/inquiry/list";
    }

    @GetMapping("/detail/{inquiryId}")
    public String detail(@PathVariable("inquiryId") int inquiryId, Model model,
                         @AuthenticationPrincipal CustomOAuth2User customOAuth2User){

        InquiryDetailDTO inquiry = inquiryService.getInquiryById(inquiryId, customOAuth2User);
        List<FileDTO> files = fileService.getFIleListByInquiryId(inquiryId);

        model.addAttribute("inquiry", inquiry);
        model.addAttribute("files", files);

        return "detail";
    }


    @GetMapping("edit/{boardId}")
    public String edit(@PathVariable("boardId") int inquiryId, Model model){
        model.addAttribute("inquiryDTO", inquiryService.goUpdateInquiry(inquiryId));

        return "edit";
    }

    @PostMapping("/edit")
    public String edit(InquiryDTO inquiry, @RequestParam("inquiryFiles") List<MultipartFile> files){
        inquiryService.updateInquiry(inquiry, files);

        return "redirect:/inquiry/list";
    }

    @PostMapping("/delete/{inquiryId}")
    public String deleteInquiry(@PathVariable int inquiryId, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        // 작성자 확인
        InquiryDetailDTO inquiry = inquiryService.getInquiryById(inquiryId, customOAuth2User);
        if (customOAuth2User.getProviderId().equals(inquiry.getProviderId())) {
            inquiryService.deleteInquiry(inquiryId);
        }
        return "redirect:/inquiry/list";
    }









}
