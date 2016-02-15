package com.fw.web.websocket;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.jetty.websocket.api.Session;

public final class WebSocketFinal {
	
	private static ConcurrentLinkedQueue<Session> sessionQueue = new ConcurrentLinkedQueue<Session>();

	public static ConcurrentLinkedQueue<Session> getSessionqueue() {
		return sessionQueue;
	}

	public static void setSessionQueue(ConcurrentLinkedQueue<Session> sessionQueue) {
		WebSocketFinal.sessionQueue = sessionQueue;
	}
}
