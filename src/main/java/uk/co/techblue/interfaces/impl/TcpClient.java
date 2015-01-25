package uk.co.techblue.interfaces.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import uk.co.techblue.dto.ItemQuote;
import uk.co.techblue.dto.ItemQuoteConstant;
import uk.co.techblue.exception.ItemQuoteException;
import uk.co.techblue.interfaces.ItemQuoteEncoder;

public class TcpClient {
	
	private void startTcpClient() throws ItemQuoteException{
		final String hostName="localhost";
		final int port = 8183;
		Socket socket=null;
		InetAddress inetAddress=null;
		try {
			inetAddress=InetAddress.getByName(hostName);
			socket=new Socket(inetAddress,port);
			final ItemQuote itemQuote=new ItemQuote(1, "demo", 120.0);
			final ItemQuoteEncoder itemQuoteEncoder=new ItemQuoteEncoderImpl(ItemQuoteConstant.DEFAULT_ENCODING);
			final byte[] itemQuoteInBytes=itemQuoteEncoder.encode(itemQuote);
//			final InputStream inputStream=socket.getInputStream();
			final OutputStream outputStream=socket.getOutputStream();
			outputStream.write(itemQuoteInBytes);
		} catch (UnknownHostException uhe) {
			throw new ItemQuoteException(uhe);
		} catch (IOException io) {
			throw new ItemQuoteException(io);
		} finally{
			try {
				socket.close();
			} catch (IOException ioException) {
				throw new ItemQuoteException();
			}
		}
	}
	
	public static void main(final String args[]) throws ItemQuoteException{
		final TcpClient tcpClient = new TcpClient();
		for(int i=0;i<100;i++){
			tcpClient.startTcpClient();
		}
	}
}
