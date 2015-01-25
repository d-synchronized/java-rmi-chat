package uk.co.techblue.interfaces.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Logger;

import uk.co.techblue.dto.ItemQuote;
import uk.co.techblue.dto.ItemQuoteConstant;
import uk.co.techblue.exception.ItemQuoteException;
import uk.co.techblue.interfaces.ItemQuoteDecoder;

public class EchoProtocol implements Runnable{
	
	private Logger LOGGER;
	
	private Socket clientSocket;
	
	public EchoProtocol(final Socket clientSocket,final Logger logger){
		this.clientSocket=clientSocket;
		LOGGER=logger;
	}

	public void run() {
		final String hostName=clientSocket.getInetAddress().getHostName();
		final int port = clientSocket.getPort();
//		LOGGER.info("About to handle the java TCP client on host "+ hostName+", port : "+port);
		InputStream inputStream;
		try {
			inputStream = clientSocket.getInputStream();
			final ItemQuoteDecoder itemQuoteDecoder=new ItemQuoteDecoderImpl(ItemQuoteConstant.DEFAULT_ENCODING);
			final ItemQuote itemQuote=itemQuoteDecoder.decode(inputStream);
			System.out.println("Item Number : "+itemQuote.getNumber() +", Item Description : "+itemQuote.getDescription()+", Item Price : "+itemQuote.getPrice());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ItemQuoteException iqe) {
			iqe.printStackTrace();
		} 
//		LOGGER.info("Client at address specified by host '"+hostName+"' on port '"+port+"' successfully served");
	}
}
