package com.example.board.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BannerDTO {
    private Long bannerId;
    private String bannerTitle;
    private String bannerImg;
    private String bannerDate;

}
