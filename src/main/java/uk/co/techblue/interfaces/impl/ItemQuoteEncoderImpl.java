package uk.co.techblue.interfaces.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import uk.co.techblue.dto.ItemQuote;
import uk.co.techblue.dto.ItemQuoteConstant;
import uk.co.techblue.exception.ItemQuoteException;
import uk.co.techblue.interfaces.ItemQuoteEncoder;

public class ItemQuoteEncoderImpl implements ItemQuoteEncoder{
	
	private String encoding=ItemQuoteConstant.DEFAULT_ENCODING;
	
	public ItemQuoteEncoderImpl(final String encoding){
		this.encoding=encoding;
	}

	public byte[] encode(ItemQuote itemQuote) throws ItemQuoteException {
		final int itemNumber=itemQuote.getNumber();
		final String itemDescription=itemQuote.getDescription();
		final Double itemPrice=itemQuote.getPrice();
		ByteArrayOutputStream byteArrayOutputStream=null;
		try {
			byteArrayOutputStream=new ByteArrayOutputStream();
			final OutputStreamWriter outputStreamWriter=new OutputStreamWriter(byteArrayOutputStream, encoding);
			
			outputStreamWriter.write(itemNumber + " "+ itemDescription+"\n");
			outputStreamWriter.write(itemPrice.toString()+"\n");
			outputStreamWriter.flush();
			
			if(byteArrayOutputStream.size() > ItemQuoteConstant.MAX_WIRE_LENGTH){
				throw new ItemQuoteException("Encoded Length too Long");
			}
		} catch (UnsupportedEncodingException unsupportedEncodingException) {
			throw new ItemQuoteException(unsupportedEncodingException);
		} catch (IOException ioException) {
			throw new ItemQuoteException(ioException);
		}
		return byteArrayOutputStream.toByteArray();
	}

}
