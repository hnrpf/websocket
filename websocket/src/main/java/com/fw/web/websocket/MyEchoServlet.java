package com.fw.web.websocket;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class MyEchoServlet extends WebSocketServlet {

	@Override
	public void configure(WebSocketServletFactory factory) {
		// TODO Auto-generated method stub
		factory.getPolicy().setIdleTimeout(10000);  
        factory.register(ServerSocket.class);  
	}

}
