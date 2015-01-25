package uk.co.techblue.exception;

public class ItemQuoteException extends Exception {

	private static final long serialVersionUID = 5533905326774120793L;

	public ItemQuoteException() {
		super();
	}

	public ItemQuoteException(final String message) {
		super(message);
	}

	public ItemQuoteException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ItemQuoteException(final Throwable cause) {
		super(cause);
	}

}
