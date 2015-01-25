package uk.co.techblue.interfaces.impl;

import java.net.Socket;
import java.util.logging.Logger;

import uk.co.techblue.interfaces.ProtocolFactory;

public class ProtocolFactoryImpl implements ProtocolFactory{

	public Runnable createServingProtocol(Socket clientSocket, Logger logger) {
		return new EchoProtocol(clientSocket, logger);
	}

}
