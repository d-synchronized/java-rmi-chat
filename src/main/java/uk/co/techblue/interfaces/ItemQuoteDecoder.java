package uk.co.techblue.interfaces;

import java.io.InputStream;

import uk.co.techblue.dto.ItemQuote;
import uk.co.techblue.exception.ItemQuoteException;

public interface ItemQuoteDecoder {

	ItemQuote decode(InputStream inputStream) throws ItemQuoteException;

}
