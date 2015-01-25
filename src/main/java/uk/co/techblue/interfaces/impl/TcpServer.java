package uk.co.techblue.interfaces.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import uk.co.techblue.dto.ItemQuote;
import uk.co.techblue.dto.ItemQuoteConstant;
import uk.co.techblue.exception.ItemQuoteException;
import uk.co.techblue.interfaces.ItemQuoteDecoder;

public class TcpServer {
	
	public static void main(final String args[]){
		
		final int port = 8183;
		
		ServerSocket serverSocket=null;
		try {
			serverSocket= new ServerSocket(port);
			
			for(;;){
				final Socket clientSocket = serverSocket.accept();
				final InputStream inputStream=clientSocket.getInputStream();
				
				final ItemQuoteDecoder itemQuoteDecoder=new ItemQuoteDecoderImpl(ItemQuoteConstant.DEFAULT_ENCODING);
				final ItemQuote itemQuote=itemQuoteDecoder.decode(inputStream);
				
				System.out.println("Item number "+itemQuote.getNumber());
			}
		} catch (IOException io) {
			io.printStackTrace();
		} catch (ItemQuoteException iqe) {
			iqe.printStackTrace();
		}
	}

}
