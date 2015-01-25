package uk.co.techblue.interfaces;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

public interface ClientServingDispatcher {
	
	void startClientDispatching(final ServerSocket serverSocket,final Logger logger,final ProtocolFactory protocolFactory) throws IOException;

}
