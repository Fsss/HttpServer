package com.Fsss.server;

import java.util.HashMap;
import java.util.Map;

public class ServletContext {
	//为每一个servlet取别名
	//login --> com.server.Fsss.LoginServlet
	private Map<String, String> servlet;
	//url --> login
	private Map<String, String> mapping;
	
	public ServletContext() {
		servlet = new HashMap<String, String>();
		mapping = new HashMap<String, String>();
	}
	
	public Map<String, String> getServlet() {
		return servlet;
	}
	public Map<String, String> getMapping() {
		return mapping;
	}
	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
	
}
