package com.Fsss.util;

import java.io.Closeable;
import java.io.IOException;

public class HttpUtil {
	
	public static final String BLANK = " ";
	public static final String CRLF = "\r\n";
	
	public static void closeIO(Closeable... io) {
		for (Closeable temp : io) {
			if (null != temp) {
				try {
					temp.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
}
