package com.example.b_dependency.dependency;

import org.springframework.stereotype.Component;

// 스프링 컨테이너에 등럭해줘
@Component
public class DTest1 {

    public void print(){
        System.out.println("Dtest1");
    }


}
