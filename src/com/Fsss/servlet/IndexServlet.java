package com.Fsss.servlet;

import com.Fsss.server.Request;
import com.Fsss.server.Response;

public class IndexServlet extends Servlet {

	@Override
	public void doGet(Request request, Response response) throws Exception {
		response.println("<html>\r\n" + 
				"	<head>\r\n" + 
				"		<title>��¼</title>\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<form method=\"post\" action=\"http://localhost:8888/log\">\r\n" + 
				"			�û�����<input type=\"text\" name=\"name\" id=\"name\"/>\r\n" + 
				"			���룺<input type=\"password\" name=\"pwd\" id=\"pwd\"/>\r\n" + 
				"			<input type=\"submit\" value=\"��¼\"/>\r\n" + 
				"		</form>\r\n" + 
				"	</body>\r\n" + 
				"</html>");
	}

	@Override
	public void doPost(Request request, Response response) throws Exception {
		
	}
	
}
