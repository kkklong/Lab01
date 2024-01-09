package com.example.demo.Repositories;

import javax.transaction.Transactional;


import com.example.demo.vo.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
// JpaRepository(T, ID)，Ｔ=資料表的類別(Model)，ID=Primary Key的型別
public interface OrderRepository  extends JpaRepository<Order, String>  {

    @Query(value = "SELECT * FROM payment_center.order WHERE orderId=:orderId", nativeQuery = true)
    // @Param("orderId") String orderId，@Param內要與SQL語法內的參數一致，後面的函數參數則可以自訂，通常會一致，比較好辨認
    public List<Order> findByOrderId(@Param("orderId") String orderId);

    // 註記為事務交易
    @Transactional
    // 註記為資料異動
    @Modifying
    // 使用標準的SQL Insert語法來寫入資料，冒號+名稱來表示要帶入的參數
    @Query(value = "INSERT INTO payment_center.order(merchno, orderId, payType, amount, asyncUrl, syncUrl, requestTime, attach, requestCurrency, apiVersion)" +
            " VALUES (:merchno, :orderId, :payType, :amount, :asyncUrl, :syncUrl, :requestTime, :attach, :requestCurrency, :apiVersion);", nativeQuery = true)
    // 回傳Int表示新增的資料筆數，透過@Param("")來對應SQL語法裡面的參數
    public int save(@Param("merchno") String merchno, @Param("orderId") String orderId, @Param("payType") Integer payType
            , @Param("amount") Double amount, @Param("asyncUrl") String asyncUrl, @Param("syncUrl") String syncUrl
            , @Param("requestTime") String requestTime, @Param("attach") String attach, @Param("requestCurrency") String requestCurrency
            , @Param("apiVersion") String apiVersion);



}
