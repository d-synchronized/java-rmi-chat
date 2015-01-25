package uk.co.techblue.interfaces.impl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class TcpServer {
	
	public static void main(final String args[]){
		
		final int port = 8183;
		
		final Logger LOGGER=null;
		
		ServerSocket serverSocket=null;
		try {
			serverSocket= new ServerSocket(port);
			
			for(;;){
				final Socket clientSocket = serverSocket.accept();
				final EchoProtocol echoProtocol=new EchoProtocol(clientSocket, LOGGER);
				final Thread clientServingThread = new Thread(echoProtocol);
				clientServingThread.start();
				System.out.println("Created and started thread -: "+clientServingThread.getName());
			}
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

}
