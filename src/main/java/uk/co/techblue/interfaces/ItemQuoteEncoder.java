package uk.co.techblue.interfaces;

import uk.co.techblue.dto.ItemQuote;
import uk.co.techblue.exception.ItemQuoteException;

public interface ItemQuoteEncoder {
	
	byte[] encode(ItemQuote itemQuote) throws ItemQuoteException;
	
}
