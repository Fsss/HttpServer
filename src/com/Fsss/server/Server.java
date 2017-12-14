package com.Fsss.server;

import java.io.IOException;
import java.net.ServerSocket;

import com.Fsss.util.HttpUtil;

public class Server {
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
	
	private ServerSocket server;
	private boolean isShutDown = false;
	
	public void start() {
		start(8888);
	}
	
	public void start(int port) {
		try {
			server = new ServerSocket(port);
			this.receiveLoop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		isShutDown = true;
		HttpUtil.closeIO(server);
	}
	
	private void receiveLoop() {
		try {
			while (!isShutDown) {
				new Thread(new Dispatcher(server.accept())).start();
			}
		} catch (IOException e) {
			stop();
		}
	}
	
}










