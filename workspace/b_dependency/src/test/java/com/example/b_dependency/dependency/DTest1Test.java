package com.example.b_dependency.dependency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DTest1Test {

    // 필드 주입
//    @Autowired
//    private DTest1 dTest1;

    // setter 주입
//    private DTest1 dTest1;
//
//    @Autowired
//    public void  setDTest(DTest1 dTest1){
//         this.dTest1 = dTest1;
//    }

    // 생성자 주입
//    private final DTest1 dTest1;
//
//    @Autowired
    // @Autowired는 test완경에서 무조건 붙여줘야 합니다
//    public DTest1Test(DTest1 dTest1){
//         this.dTest1 = dTest1;
//    }

//    @Test
//    void print2() {
////        DTest1 test1 = new DTest1();
////        test1.print();
//        dTest1.print();
//    }





}