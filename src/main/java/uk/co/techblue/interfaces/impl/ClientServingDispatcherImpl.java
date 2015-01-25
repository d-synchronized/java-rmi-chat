package uk.co.techblue.interfaces.impl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import uk.co.techblue.interfaces.ClientServingDispatcher;
import uk.co.techblue.interfaces.ProtocolFactory;

public class ClientServingDispatcherImpl implements ClientServingDispatcher {

	public void startClientDispatching(ServerSocket serverSocket,
			Logger logger, ProtocolFactory protocolFactory) throws IOException {
		for (;;) {
			final Socket clientSocket = serverSocket.accept();
			final Runnable protocol = protocolFactory.createServingProtocol(clientSocket, logger);
			final Thread thread = new Thread(protocol);
			thread.start();
			System.out.println("Created and started thread -: "+thread.getName());
		}
	}

}
