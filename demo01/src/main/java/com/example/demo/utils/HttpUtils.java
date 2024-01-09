package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 通用http发送方法
 *
 */
public class HttpUtils
{

    /**
     * 创建请求参数字符串
     * (不对值进行URL编码)
     * (忽略空值)
     * @param params
     * @return
     */
    public static String buildParamString(Map<String, Object> params) {
        return buildParamString(new TreeMap<>(params), true);
    }

    /**
     * 创建请求参数字符串
     * (不对值进行URL编码)
     * (忽略空值)
     * @param params
     * @return
     */
    public static String buildParamString(TreeMap<String, Object> params) {
        return buildParamString(params, true);
    }
    /**
     * 创建请求参数字符串
     * (不对值进行URL编码)
     * @param params
     * @param isIgnoreEmpty 是否忽略空值
     * @return
     */
    public static String buildParamString(TreeMap<String, Object> params, boolean isIgnoreEmpty) {
        return buildParamString(params, isIgnoreEmpty, false);
    }

    /**
     * 创建请求参数字符串
     * @param params
     * @param isIgnoreEmpty 是否忽略空值
     * @param isUrlEncode 是否对值进行URL编码
     * @return
     */
    public static String buildParamString(TreeMap<String, Object> params, boolean isIgnoreEmpty, boolean isUrlEncode) {
        StringBuilder sb = new StringBuilder();
        for (Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Object> ele = it.next();
            String value = ele.getValue() == null ? "" : ele.getValue().toString();
            if (isIgnoreEmpty && (StringUtils.isEmpty(value))) {
                continue;
            }
            if (isUrlEncode) {
                try {
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
            sb.append("&").append(ele.getKey()).append("=").append(value);
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }


}
