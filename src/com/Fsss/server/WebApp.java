package com.Fsss.server;

import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.Fsss.servlet.Servlet;

public class WebApp {
	
	private static ServletContext context;
	static {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser sax = factory.newSAXParser();
			WebHandler web = new WebHandler();
			sax.parse(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("WEB_INFO/web.xml")
					, web);
			
			context = new ServletContext();
			Map<String, String> servlet = context.getServlet();
			Map<String, String> mapping = context.getMapping();
			
			for (Entity entity : web.getEntityList()) {
				servlet.put(entity.getName(), entity.getClz());
			}
			for (Mapping mapp : web.getMappingList()) {
				for (String url : mapp.getUrlPattern()) {
					mapping.put(url, mapp.getName());
				}
			}
		} catch (Exception e) {
			
		}
	}
	
	public static Servlet getServlet(String url) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (null == url || url.trim().equals("")) {
			return null;
		}
		String name = context.getServlet().get(context.getMapping().get(url));
		return (Servlet)Class.forName(name).newInstance();
	}
	
}
