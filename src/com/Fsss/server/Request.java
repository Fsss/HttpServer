package com.Fsss.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Fsss.util.HttpUtil;

public class Request {
	
	//请求方法/请求资源/请求参数/流/请求信息
	private String method;
	private String url;
	private Map<String, List<String>> parameterMapValues;
	private InputStream is;
	private String requestInfo;
	
	public Request() {
		method = "";
		url = "";
		parameterMapValues = new HashMap<String, List<String>>();
		requestInfo = "";
	}
	
	public Request(InputStream is) {
		this();
		this.is = is;
		readRequest();
	}
	
	private void readRequest() {
		try {
			byte[] data = new byte[20480];
			int len = is.read(data);
			if (len > 0) {
				requestInfo = new String(data, 0, len);
			}
		} catch (IOException e) {
			requestInfo = "";
		}
		System.out.println(requestInfo);
		parseRequestInfo();
	}
	
	/**
	 * 分析请求信息
	 */
	private void parseRequestInfo() {
		if (null == requestInfo || (requestInfo = requestInfo.trim()).equals("")) {
			return;
		}
		/**
		 * 请求方式  请求路径  请求参数(可能存在)
		 * GET /index.html?name=123&pwd=123 HTTP/1.1
		 * 为POST的话，请求参数可能在最后的正文中
		 */
		String paramString = "";
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(HttpUtil.CRLF));
		int idx = requestInfo.indexOf("/");
		this.method = firstLine.substring(0, idx).trim();
		String urlStr = firstLine.substring(idx, firstLine.indexOf("HTTP/")).trim();
		if (this.method.equalsIgnoreCase("POST")) {
			this.url = urlStr;
			paramString = requestInfo.substring(requestInfo.lastIndexOf(HttpUtil.CRLF)).trim();
		} else if (this.method.equalsIgnoreCase("GET")) {
			if (urlStr.contains("?")) {
				String[] urlArray = urlStr.split("\\?");
				this.url = urlArray[0];
				paramString = urlArray[1];
			} else {
				this.url = urlStr;
			}
		}
		if (!paramString.equals("")) {
			parseParams(paramString);
		}
	}
	
	private void parseParams(String paramString) {
		String[] parameters = paramString.split("&");
		for (String temp : parameters) {
			String[] keyValues = temp.split("=");
			if (1 == keyValues.length) {
				keyValues = Arrays.copyOf(keyValues, 2);
				keyValues[1] = null;
			}
			String key = keyValues[0].trim();
			String value = null == keyValues[1] ? null : decode(keyValues[1].trim(), "gbk");
			if (!parameterMapValues.containsKey(key)) {
				parameterMapValues.put(key, new ArrayList<String>());
			}
			List<String> values = parameterMapValues.get(key);
			values.add(value);
		}
	}
	
	private String decode(String value, String code) {
		try {
			return java.net.URLDecoder.decode(value, code);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public String getParameter(String key) {
		String[] values = getParameterValues(key);
		if (null == values) {
			return "";
		} else {
			return values[0];
		}
	}
	
	public String[] getParameterValues(String key) {
		List<String> values = null;
		if (null == (values = parameterMapValues.get(key))) {
			return null;
		} else {
			return values.toArray(new String[0]);
		}
	}

	public String getUrl() {
		return url;
	}
	
}






