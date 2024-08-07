package com.example.board.controller.pay;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay/rest")
public class PayRestController {

    @PostMapping("/chargingOk")
    public String chargingOk(@RequestParam String result ) {

        System.out.println(result);

        return result + "  OK";
    }

}
