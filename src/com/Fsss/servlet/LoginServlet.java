package com.Fsss.servlet;

import com.Fsss.server.Request;
import com.Fsss.server.Response;

public class LoginServlet extends Servlet {
	
	public boolean login(String name, String pwd) {
		return name.equals("Fsss") && pwd.equals("123456");
	}
	
	@Override
	public void doGet(Request request, Response response)  throws Exception {
		if (login(request.getParameter("name"), request.getParameter("pwd"))) {
			response.println("��¼�ɹ�");
		} else {
			response.println("��¼ʧ��");
		}
	}

	@Override
	public void doPost(Request request, Response response) throws Exception {
		
	}

}
