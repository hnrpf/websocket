package com.fw.web.websocket;

import org.eclipse.jetty.server.Server;  
import org.eclipse.jetty.server.handler.DefaultHandler;  
import org.eclipse.jetty.server.handler.HandlerList;  
import org.eclipse.jetty.servlet.ServletContextHandler;  
import org.eclipse.jetty.servlet.ServletHolder;  
  
public class WebServer {  
    public static void main(String[] args) {  
        Server server = new Server(8080);    
        try {  
        	//handle thread
            SessionThread thread = new SessionThread();
            thread.start();
        	
            HandlerList handlerList = new HandlerList();    
            ServletContextHandler context = new ServletContextHandler(    
                    ServletContextHandler.SESSIONS);    
            context.setContextPath("/");  
            context.addServlet(new ServletHolder(new MyEchoServlet()), "/echo");    
            handlerList.addHandler(context);    
            handlerList.addHandler(new DefaultHandler());    
            server.setHandler(handlerList);    
            server.start();    
            server.join();    
            
            
        } catch (Exception e) {    
            
            e.printStackTrace();    
        }    
    }  
  
  
}  
