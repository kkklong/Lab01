package com.example.demo.controller;

import com.example.demo.Repositories.OrderRepository;
import com.example.demo.service.RechargeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.HttpUtils;
import com.example.demo.utils.Md5Utils;
import com.example.demo.vo.CallbackParam;
import com.example.demo.vo.Order;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import static com.example.demo.utils.HttpClientUtils.doPost;



@RestController
@RequestMapping( "/api/pay")
public class RechargeController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RechargeController.class);
    @Resource
    private RechargeService rechargeService;
@Autowired
    OrderRepository orderRepository;

    @GetMapping ( "/onlinepay")
    public String onlinepay(String merchno, String orderId, Integer payType, Double amount, String asyncUrl, String syncUrl,
                            String requestTime, String attach, String requestCurrency, String apiVersion) throws Exception {
LOGGER.info("{}&{}&{}&{}&{}&{}&{}&{}&{}&{}",merchno,  orderId,  payType,  amount,  asyncUrl,  syncUrl,
        requestTime,  attach,  requestCurrency,  apiVersion);

        return rechargeService.onlinePay( merchno,  orderId,  payType,  amount,  asyncUrl,  syncUrl,
                 requestTime,  attach,  requestCurrency,  apiVersion);



    }

//    /**
//     * 异步回调通知接口
//     * @param callbackParam
//     * @return
//     */
//    @RequestMapping("/asyncCallback")
//    @ResponseBody



}
