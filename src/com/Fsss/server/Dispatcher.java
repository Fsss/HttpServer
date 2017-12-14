package com.Fsss.server;

import java.io.IOException;
import java.net.Socket;

import com.Fsss.servlet.Servlet;
import com.Fsss.util.HttpUtil;

public class Dispatcher implements Runnable {
	
	private int code = 200;
	private Socket client;
	private Request request;
	private Response response;
	public Dispatcher(Socket client) {
		this.client = client;
		try {
			this.request = new Request(client.getInputStream());
			this.response = new Response(client.getOutputStream());
		} catch (IOException e) {
			code = 500;
		}
	}
	
	@Override
	public void run() {
		try {
			Servlet servlet = WebApp.getServlet(request.getUrl());
			if (null == servlet) {
				this.code = 404;  //找不到对应的处理
			} else {
				servlet.service(request, response);
			}
			response.pushToClient(code);
		} catch (Exception e) {
			this.code = 500;
		}
		try {
			response.pushToClient(500);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpUtil.closeIO(client);
	}
	
}



