package com.example.demo.service;

import com.example.demo.OrderDao.OrderDaoImpl;
import com.example.demo.api.xxPayApi;
import com.example.demo.vo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RechargeService {
@Autowired
OrderDaoImpl orderDaoImpl;
    private static final Logger LOGGER = LoggerFactory.getLogger(RechargeService.class);
    public String onlinePay(String merchno, String orderId, Integer payType, Double amount, String asyncUrl, String syncUrl,
                            String requestTime, String attach, String requestCurrency, String apiVersion) throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setMerchno(merchno);
        order.setOrderId(orderId);
        order.setPayType(payType);
        order.setAmount(amount);
        order.setAsyncUrl(asyncUrl);
        order.setSyncUrl(syncUrl);
        order.setRequestTime(requestTime);
        order.setAttach(attach);
        order.setRequestCurrency(requestCurrency);
        order.setApiVersion(apiVersion);
        LOGGER.info("Order data: " + order.toString());
        orderDaoImpl.save(order);
        xxPayApi xxPay = new xxPayApi();

        String ReBody = xxPay.pay(order);
        return xxPay.pay(order);



    }

}
