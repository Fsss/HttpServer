package com.Fsss.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

import com.Fsss.util.HttpUtil;

public class Response {
	
	//���ĳ���
	private int contentLength = 0;
	//����
	private StringBuilder content;
	//״̬�к���Ӧͷ
	private StringBuilder headInfo;
	//��
	private BufferedWriter bw;
	
	public Response() {
		content = new StringBuilder();
		headInfo = new StringBuilder();
	}
	
	public Response(OutputStream os) {
		this();
		bw = new BufferedWriter(new OutputStreamWriter(os));
	}
	
	/**
	 * ��������
	 */
	public Response println(String info) {
		content.append(info).append(HttpUtil.CRLF);
		contentLength += (info + HttpUtil.CRLF).getBytes().length;
		return this;
	}
	
	/**
	 * ������Ӧͷ
	 */
	private void createHeadInfo(int code) {
		headInfo.append("HTTP/1.1").append(HttpUtil.BLANK).append(code).append(HttpUtil.BLANK);
		switch (code) {
		case 200:
			headInfo.append("OK");
			break;
		case 404:
			headInfo.append("NOT FOUND");
			break;
		case 505:
			headInfo.append("SERVER ERROR");
			break;
		}
		headInfo.append(HttpUtil.CRLF);
		headInfo.append("Server:tomcat server/0.0.1").append(HttpUtil.CRLF);
		headInfo.append("Date:").append(new Date()).append(HttpUtil.CRLF);
		headInfo.append("Content-type:text/html;charset=GBK").append(HttpUtil.CRLF);
		headInfo.append("Content-Length:").append(contentLength).append(HttpUtil.CRLF);
		headInfo.append(HttpUtil.CRLF);
	}
	
	/**
	 * ���͵��ͻ���
	 */
	public void pushToClient(int code) throws IOException {
		if (null == headInfo) {
			code = 500;
		}
		createHeadInfo(code);
		bw.append(headInfo.toString());
		bw.append(content.toString());
		bw.flush();
	}
	
	public void close() {
		HttpUtil.closeIO(bw);
	}
	
}




