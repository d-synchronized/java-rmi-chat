package uk.co.techblue.interfaces.impl;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

import uk.co.techblue.interfaces.ClientServingDispatcher;

public class TcpServer {
	
	public static void main(final String args[]){
		
		final int port = 8183;
		final Logger LOGGER=null;
		ServerSocket serverSocket=null;
		try {
			serverSocket= new ServerSocket(port);
			final ClientServingDispatcher clientServingDispatcher=new ClientServingDispatcherImpl();
			clientServingDispatcher.startClientDispatching(serverSocket, LOGGER, new ProtocolFactoryImpl());
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

}
