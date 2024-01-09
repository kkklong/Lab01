package com.example.demo.OrderDao;

import com.example.demo.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;

@Repository
public class OrderDaoImpl {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    public void save(Order order) {

        jdbcTemplate.update( "insert into `order` (`merchno`, `orderId`, `payType`, `amount`, `asyncUrl`, `syncUrl`, `requestTime`, `attach`, `requestCurrency`, `apiVersion`)" +
                        "values" +
                        "(?,?,?,?,?,?,?,?,?,?)",order.getMerchno(), order.getOrderId(), order.getPayType().toString(), order.getAmount().toString(), order.getAsyncUrl(), order.getSyncUrl(), order.getRequestTime(), order.getAttach(),
                order.getRequestCurrency(), order.getApiVersion().toString());
    }

//    public void save(String merchno, String orderId, Integer payType, Double amount, String asyncUrl, String syncUrl, String requestTime, String attach, String requestCurrency, String apiVersion) {
//        jdbcTemplate.batchUpdate( "insert into `order` ('merchno', 'orderId', 'payType', 'amount', 'asyncUrl', 'syncUrl', 'requestTime', 'attach', 'requestCurrency', 'apiVersion') " +
//                "values" +
//                " (?,?,?)"
//        );

}