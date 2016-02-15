package com.fw.web.websocket;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class SimpleSocket {

	@SuppressWarnings("unused")  
    private Session session;  
	
	private final CountDownLatch closeLatch;
	
	public SimpleSocket() {
		this.closeLatch = new CountDownLatch(1); 
	}
	
	@OnWebSocketClose  
    public void onClose(int statusCode, String reason) {  
        System.out.printf("Connection closed: %d - %s%n", statusCode, reason);  
        this.session = null;  
    }  
	
	@OnWebSocketConnect  
    public void onConnect(Session session) {  
        System.out.printf("Got connect: %s%n", session);  
        this.session = session;  
        try {  
            Future<Void> fut;  
            fut = session.getRemote().sendStringByFuture("Hello");  
            fut.get(20, TimeUnit.SECONDS);  
            fut = session.getRemote().sendStringByFuture("Thanks for the conversation.");  
            fut.get(20, TimeUnit.SECONDS);  
//            session.close(StatusCode.NORMAL, "I'm done");  
        } catch (Throwable t) {  
            t.printStackTrace();  
        }  
    }  
	
	@OnWebSocketMessage  
    public void onMessage(String msg) {  
        System.out.printf("Got msg: %s%n", msg);  
    }

	public void awaitClose() throws InterruptedException {  
	    this.closeLatch.await();
	}    
}
