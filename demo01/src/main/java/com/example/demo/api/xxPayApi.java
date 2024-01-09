package com.example.demo.api;

import com.example.demo.service.RechargeService;
import com.example.demo.utils.HttpUtils;
import com.example.demo.utils.Md5Utils;
import com.example.demo.vo.CallbackParam;
import com.example.demo.vo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;

import static com.example.demo.utils.HttpClientUtils.doPost;

public class xxPayApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(xxPayApi.class);
    // 商户秘钥
    private String secretKey = "secretKey";

    public String pay(Order order) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("merchno", order.getMerchno());
        params.put("orderId", order.getOrderId());
        params.put("payType", String.valueOf(order.getPayType()));
        params.put("amount", String.valueOf(order.getAmount()));
        params.put("asyncUrl", order.getAsyncUrl());
        params.put("syncUrl", order.getSyncUrl());
        params.put("requestTime", order.getRequestTime() );
        params.put("attach", order.getAttach());
        params.put("requestCurrency",order.getRequestCurrency());// 请求货币
        params.put("apiVersion",order.getApiVersion());// api版本号

        String toSignStr = signStr(params);
        toSignStr += "&secretKey=" + secretKey;
        String sign = Md5Utils.hash(toSignStr).toLowerCase();

        LOGGER.info("sign param: " + toSignStr);
        params.put("sign", sign);
//        return "OK";
        try {
            String response = doPost("http://localhost:8002/api/pay/xxPay",params, null).toString();
            LOGGER.info("3rdParty response: " + response);
            return response ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object asyncCallback(CallbackParam callbackParam) {

        TreeMap<String, Object> params = new TreeMap();
        params.put("merchno", callbackParam.getMerchno());
        params.put("orderId", callbackParam.getOrderId());
        params.put("amount", callbackParam.getAmount());
        params.put("payType", callbackParam.getPayType());
        params.put("attach", callbackParam.getAttach());
        params.put("status", callbackParam.getStatus());
        params.put("requestCurrency", callbackParam.getRequestCurrency());
        params.put("apiVersion", callbackParam.getApiVersion());

        String paramStr = HttpUtils.buildParamString(params);
        String toSignStr = paramStr + "&secretKey=" + secretKey;

        String calcSign = Md5Utils.hash(toSignStr).toLowerCase();
        boolean isValidate = calcSign.equals(callbackParam.getSign());

        LOGGER.info("sign param: " + toSignStr);
        if (isValidate) {
            ///////////////////////////////
            // 省略 确认订单，并入账 步骤
            ///////////////////////////////
        }
        System.out.println(HttpUtils.buildParamString(params) + ":::::::::::::" + isValidate) ;

        return isValidate ? "success" : "fail";
    }

    private static String signStr(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        map.forEach((key, value) ->
                sb.append(key + "=" + value + "&")
        ); return sb.deleteCharAt(sb.lastIndexOf("&")).toString();
    }


}
