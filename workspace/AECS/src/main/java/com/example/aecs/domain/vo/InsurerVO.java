package com.example.aecs.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class InsurerVO {
    private int insurerId;
    private String insureNumber;
    private String insurerName;
    private String insurerPhoneNumber;
    private String insurerRequest;
}
