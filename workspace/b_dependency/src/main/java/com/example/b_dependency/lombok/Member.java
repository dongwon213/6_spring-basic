package com.example.b_dependency.lombok;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
//@Data

public class Member {

    @NonNull
    private String name;

    private int age;
    private String gender;
    private String address;


}
