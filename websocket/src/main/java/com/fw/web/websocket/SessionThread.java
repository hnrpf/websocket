package com.fw.web.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.jetty.websocket.api.Session;

public class SessionThread extends Thread {

	@Override
	public void run() {
		int count = 0;
		while(true){
			System.out.println(WebSocketFinal.getSessionqueue().size());
			for (Session session : WebSocketFinal.getSessionqueue()) {
				try {
					session.getRemote().sendString("handle thread: " + count++);
					Thread.sleep(2000);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
