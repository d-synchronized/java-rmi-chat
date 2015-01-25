package uk.co.techblue.interfaces;

import java.net.Socket;
import java.util.logging.Logger;

public interface ProtocolFactory {
	
	Runnable createServingProtocol(final Socket clientSocket,final Logger logger);

}
