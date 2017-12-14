package com.Fsss.server;

import java.util.ArrayList;
import java.util.List;

public class Mapping {
	private String name;
	private List<String> urlPattern;
	
	public Mapping() {
		urlPattern = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	public List<String> getUrlPattern() {
		return urlPattern;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUrlPattern(List<String> urlPattern) {
		this.urlPattern = urlPattern;
	}
	
}
