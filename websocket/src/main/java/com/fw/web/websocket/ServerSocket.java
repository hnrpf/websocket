package com.fw.web.websocket;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ServerSocket {

	 private Session session;  
	 
	 	@OnWebSocketClose  
	    public void onClose(int statusCode, String reason) {  
	        System.out.printf("Connection closed: %d - %s%n", statusCode, reason);  
	        this.session = null;
	        WebSocketFinal.getSessionqueue().remove(session);
	    }  
	   
	    @OnWebSocketConnect  
	    public void onConnect(Session session) {  
	        System.out.printf("Got connect: %s%n", session);  
	        this.session = session;  
	        WebSocketFinal.getSessionqueue().add(session);
	    }  
	  
	    @OnWebSocketMessage  
	    public void onMessage(String msg) {  
	        System.out.printf("Got msg: %s%n", msg);  
	        try {  
	        session.getRemote().sendString("RESPONSE : " + msg);  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	    }  
}
