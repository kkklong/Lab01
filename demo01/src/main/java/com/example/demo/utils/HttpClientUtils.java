package com.example.demo.utils;

//import com.cz.framework.exception.BusinessException;
import org.apache.commons.collections.MapUtils;
import org.apache.http.client.config.RequestConfig;
//import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	private static final PoolingHttpClientConnectionManager connMgr;
	private static final RequestConfig requestConfig;
	private static final int MAX_TIMEOUT = 300000;
	private static final String POST_PARAMS_PATTERN_JSON = "json";
	private static final String POST_PARAMS_PATTERN_FORM = "form";

	static {
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connMgr.setMaxTotal(20000);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		// 设置读取超时
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
		// 在提交请求之前 测试连接是否可用
		configBuilder.setStaleConnectionCheckEnabled(true);
		requestConfig = configBuilder.build();
	}

	/**
	 * POST请求 ,请求参数格式为JSON
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url,
			String params) throws Exception {
		return post(url, POST_PARAMS_PATTERN_JSON, params, null);
	}

	/**
	 * POST请求 ,请求参数格式为JSON
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url,
			String params, Header[] headers) throws Exception {
		return post(url, POST_PARAMS_PATTERN_JSON, params, headers);
	}

	/**
	 * POST请求 ,请求参数格式为form表单
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url,
			Map<String, String> params, Header[] headers) throws Exception  {
		return post(url, POST_PARAMS_PATTERN_FORM, params, headers);
	}

	/**
	 * PUT请求
	 *
	 * @param apiUrl api接口地址
	 * @param json 请求参数json对象
	 * @return
	 * @throws Exception
	 */
	public static String doPut(String apiUrl, Object json, Header[] header) throws Exception {

		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		String httpStr = null;
		HttpPut httpPut = new HttpPut(apiUrl);
		CloseableHttpResponse response = null;
		try {
//			httpPut.setConfig(requestConfig);
			httpPut.setHeaders(header);
			StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");

			httpPut.setEntity(stringEntity);
			response = httpClient.execute(httpPut);
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, "UTF-8");
		} catch (IOException e) {
			throw e;
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException ex) {
					logger.error("关闭http流发生异常", ex);
				}
			}
			httpClient.close();
		}
		return httpStr;
	}

	/**
	 * GET请求
	 *
	 * @param apiUrl
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String apiUrl, Header[] headers)
			throws Exception {

		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		String httpStr = null;
		HttpGet httpGet = new HttpGet(apiUrl);
		CloseableHttpResponse response = null;

		try {
//			httpGet.setConfig(requestConfig);
			httpGet.setHeaders(headers);
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, "UTF-8");
		} catch (IOException e) {
			throw e;
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException ex) {
					logger.error("关闭http流发生异常", ex);
				}
			}
			httpClient.close();
		}

		return httpStr;
	}

	@SuppressWarnings("unchecked")
	private static String post(String url, String pattern, Object params, Header[] headers) throws Exception {

		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		StringEntity stringEntity = null;


		try {
//			httpPost.setConfig(requestConfig);
			httpPost.setHeaders(headers);

			switch(pattern) {

				case POST_PARAMS_PATTERN_JSON:

					stringEntity = new StringEntity((String)params, "UTF-8");// 解决中文乱码问题
					stringEntity.setContentEncoding("UTF-8");
					stringEntity.setContentType("application/json");
					break;
				case POST_PARAMS_PATTERN_FORM:

					// 创建参数队列
					List<NameValuePair> formParams = new ArrayList<NameValuePair>();

					Map<String, String> map = (Map<String, String>) params;
					for (Map.Entry<String, String> entry : map.entrySet()) {
						formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
					}

					stringEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
					break;
			}

			//参数转码
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, "UTF-8");
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode != 200 && statusCode != 201){
				logger.error("http error url=" + url + ",statusCode="+ statusCode + ",json=" + httpStr);
//				throw new BusinessException("http_error/" + statusCode, httpStr, null);
			}
			//释放连接
		} catch (IOException e) {
			throw e;
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException ex) {
					logger.error("关闭http流发生异常", ex);
				}
			}
			httpClient.close();
		}
		return httpStr;
	}

	/**
	 * 转queryStr参数
	 *
	 * @param map
	 * @return
	 */
	public static String getQueryStr(Map<String, Object> map) {
		if (MapUtils.isEmpty(map)) {
			return null;
		}
		StringBuilder queryStr = new StringBuilder();
		map.forEach((k, v) -> queryStr.append(k).append("=").append(String.valueOf(v)).append("&"));
		return queryStr.deleteCharAt(queryStr.lastIndexOf("&")).toString();
	}

	public static String doPostSetRelativeRedirectsAllowed(String url, Object params, Header[] headers) throws Exception {
		LaxRedirectStrategy LaxRedirectStrategy=new LaxRedirectStrategy();
		CloseableHttpClient httpClient = HttpClients.custom().setRedirectStrategy(LaxRedirectStrategy).setDefaultRequestConfig(requestConfig).build();
		String httpStr;
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		StringEntity stringEntity;

		try {
			httpPost.setHeaders(headers);
			stringEntity = new StringEntity((String)params, "UTF-8");// 解决中文乱码问题
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");

			//参数转码
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, "UTF-8");
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode != 200){
				logger.error("http error url=" + url + ",statusCode="+ statusCode + ",json=" + httpStr);
//				throw new BusinessException("http_error/" + statusCode, httpStr, null);
			}
			//释放连接
//		} catch (IOException | BusinessException e) {
		} catch (IOException e) {
			throw e;
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException ex) {
					logger.error("关闭http流发生异常", ex);
				}
			}
			httpClient.close();
		}
		return httpStr;
	}
}
