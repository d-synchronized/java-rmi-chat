package uk.co.techblue.interfaces.impl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import uk.co.techblue.interfaces.ClientServingDispatcher;
import uk.co.techblue.interfaces.ProtocolFactory;

public class ClientServingDispatcherImpl implements ClientServingDispatcher {
	
	private int numberOfThreads;
	
	private static final String NUMBER_OF_THREAD = "8";
	
	private static final String THREAD_PROPERTY="Threads";
	
	public ClientServingDispatcherImpl(){
		numberOfThreads = Integer.parseInt(System.getProperty(THREAD_PROPERTY, NUMBER_OF_THREAD));
	}
	
	public void startClientDispatching(final ServerSocket serverSocket,final Logger logger,final ProtocolFactory protocolFactory) 
			throws IOException {
		for(int i=0;i<(numberOfThreads-1);i++){
			Thread thread = new Thread(){
				public void run(){
					dispatcherLoop(serverSocket, logger, protocolFactory);
				}
			};
			thread.start();
			System.out.println("Created and started thread -: "+thread.getName());
		}
		dispatcherLoop(serverSocket, logger, protocolFactory);
	}
	
	private void dispatcherLoop(final ServerSocket serverSocket, final Logger logger, final ProtocolFactory protocolFactory) {
		for (;;) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			if (clientSocket == null) {
				return;
			}
			final Runnable protocol = protocolFactory.createServingProtocol(clientSocket, logger);
			protocol.run();
		}
	}

}
