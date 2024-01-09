package com.example.demo.demo;

import com.example.demo.service.RechargeService;
import org.assertj.core.util.DateUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestPay {

    @Test
    public void testPay() throws Exception {
        RechargeService rechargeService = new RechargeService();
        rechargeService.onlinePay("kkk111", "202311230001", 01, 100.00, "http://127.0.0.1/asy", "http://127.0.0.1/syn",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")), "attach", "cny", "1.0.0");

    }
}
