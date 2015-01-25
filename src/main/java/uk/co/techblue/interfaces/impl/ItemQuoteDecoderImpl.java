package uk.co.techblue.interfaces.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import uk.co.techblue.dto.ItemQuote;
import uk.co.techblue.dto.ItemQuoteConstant;
import uk.co.techblue.exception.ItemQuoteException;
import uk.co.techblue.interfaces.ItemQuoteDecoder;
import uk.co.techblue.util.Framer;

public class ItemQuoteDecoderImpl implements ItemQuoteDecoder{
	
	private String encoding=ItemQuoteConstant.DEFAULT_ENCODING;
	
	public ItemQuoteDecoderImpl(final String encoding){
		this.encoding=encoding;
	}

	public ItemQuote decode(final InputStream inputStream) throws ItemQuoteException {
		byte[] spaceInBytes=" ".getBytes();
		byte[] newLineInBytes="\n".getBytes();
		
		int itemNumber=0;
		String itemDescription=null;
		double itemPrice=0.0;
		try{
			itemNumber=Integer.valueOf(new String(Framer.nextToken(inputStream, spaceInBytes),encoding));
			itemDescription=new String(Framer.nextToken(inputStream, newLineInBytes),encoding);
			itemPrice = Double.valueOf(new String(Framer.nextToken(inputStream, newLineInBytes),encoding));
		}catch(final UnsupportedEncodingException usee){
			throw new ItemQuoteException(usee);
		} catch (NumberFormatException nfe) {
			throw new ItemQuoteException(nfe);
		} catch (IOException io) {
			throw new ItemQuoteException(io);
		}
		return new ItemQuote(itemNumber, itemDescription, itemPrice);
	}

}
