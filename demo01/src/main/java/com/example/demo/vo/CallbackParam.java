package com.example.demo.vo;


import lombok.Data;
import lombok.ToString;

/**
 * 回调参数
 */
@Data
@ToString
public class CallbackParam
{


	/** 商户号 */
	private String merchno;
	/** 商户订单号 */
	private String orderId;
	/** 订单金额（元） */
	private String amount;
	/** 支付类型 */
	private String payType;
	/** 商户附加信息 */
	private String attach;
	/** 订单状态 */
	private String status;
	/** 签名 */
	private String sign;
	/** 请求货币（1：人民币；2：USDT；3：越南盾；4：印度卢比） */
	private String requestCurrency;
	/** api接口版本 */
	private String apiVersion;
/*
	public String getMerchno() {
		return merchno;
	}

	public void setMerchno(String merchno) {
		this.merchno = merchno;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRequestCurrency() {
		return requestCurrency;
	}

	public void setRequestCurrency(String requestCurrency) {
		this.requestCurrency = requestCurrency;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CallbackParam{");
		sb.append("merchno='").append(merchno).append('\'');
		sb.append(", orderId='").append(orderId).append('\'');
		sb.append(", amount='").append(amount).append('\'');
		sb.append(", payType='").append(payType).append('\'');
		sb.append(", attach='").append(attach).append('\'');
		sb.append(", status='").append(status).append('\'');
		sb.append(", sign='").append(sign).append('\'');
		sb.append(", requestCurrency='").append(requestCurrency).append('\'');
		sb.append(", apiVersion='").append(apiVersion).append('\'');
		sb.append('}');
		return sb.toString();
	}

 */
}
