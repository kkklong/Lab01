package com.example.demo.vo;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;


@ToString
@Entity
@Table(name = "order")

public class Order
{

	private Long id;
	/** 商户号 */
	private String merchno;
	/** 商户订单号 */
	private String orderId;
	/** 支付类型 */
	private Integer payType;
	/** 订单金额（元） */
	private Double amount;
	/** 异步回调地址 */
	private String asyncUrl;
	/** 同步回调地址 */
	private String syncUrl;
	/** 商户请求时间 */
	private String requestTime;
	/** 商户附加信息 */
	private String attach;
	/** 请求货币（1：人民币；2：USDT；3：越南盾；4：印度卢比） */
	private String requestCurrency;
	/** 版本号 */
	private String apiVersion;

	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "merchno")
	public String getMerchno() {
		return merchno;
	}

	public void setMerchno(String merchno) {
		this.merchno = merchno;
	}
	@Column(name = "orderId")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Column(name = "payType")
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	@Column(name = "amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Column(name = "asyncUrl")
	public String getAsyncUrl() {
		return asyncUrl;
	}

	public void setAsyncUrl(String asyncUrl) {
		this.asyncUrl = asyncUrl;
	}
	@Column(name = "syncUrl")
	public String getSyncUrl() {
		return syncUrl;
	}
	public void setSyncUrl(String syncUrl) {
		this.syncUrl = syncUrl;
	}
	@Column(name = "requestTime")
	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	@Column(name = "attach")
	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}
	@Column(name = "requestCurrency")
	public String getRequestCurrency() {
		return requestCurrency;
	}

	public void setRequestCurrency(String requestCurrency) {
		this.requestCurrency = requestCurrency;
	}
	@Column(name = "apiVersion")
	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Order{");
		sb.append("id='").append(id).append('\'');
		sb.append("merchno='").append(merchno).append('\'');
		sb.append(", orderId='").append(orderId).append('\'');
		sb.append(", payType=").append(payType);
		sb.append(", amount=").append(amount);
		sb.append(", asyncUrl='").append(asyncUrl).append('\'');
		sb.append(", syncUrl='").append(syncUrl).append('\'');
		sb.append(", requestTime=").append(requestTime);
		sb.append(", attach='").append(attach).append('\'');
		sb.append(", requestCurrency='").append(requestCurrency).append('\'');
		sb.append(", apiVersion='").append(apiVersion).append('\'');
		sb.append('}');
		return sb.toString();
	}


}
