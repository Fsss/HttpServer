package com.Fsss.servlet;

import com.Fsss.server.Request;
import com.Fsss.server.Response;

public class RegisterServlet extends Servlet {

	@Override
	public void doGet(Request request, Response response) throws Exception {
		
	}

	@Override
	public void doPost(Request request, Response response) throws Exception {
		response.println(
				"<html>\r\n" + 
				"	<head>\r\n" + 
				"		<title>注册</title>\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		你的用户名为："+ request.getParameter("name") + "\r\n" + 
				"	</body>\r\n" + 
				"</html>");
	}
	
}
