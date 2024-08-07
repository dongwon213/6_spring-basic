package com.example.aecs.controller;

import com.example.aecs.domain.dto.CarDTO;
import com.example.aecs.domain.oauth.CustomOAuth2User;
import com.example.aecs.service.CarService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class MypageController {

    private final CarService carService;

    public MypageController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/mypage")
    public String myPage(Model model, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        String providerId = customOAuth2User.getProviderId(); // 현재 사용자의 providerId를 가져오는 방법
        List<CarDTO> cars = carService.getCarsByProviderId(providerId);
        model.addAttribute("cars", cars);
        return "mypage";
    }

    @GetMapping("/mypage/register")
    public String registerForm(Model model, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        model.addAttribute("providerId", customOAuth2User.getProviderId()); // providerId를 모델에 추가
        model.addAttribute("carDTO", new CarDTO());
        return "register";
    }

    @PostMapping("/mypage/register")
    public String register(CarDTO carDTO, @RequestParam("carImage") MultipartFile carImage, @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        try {
            carDTO.setProviderId(customOAuth2User.getProviderId()); // providerId 설정
            carService.registerCar(carDTO, carImage);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "redirect:/mypage";
    }
}
