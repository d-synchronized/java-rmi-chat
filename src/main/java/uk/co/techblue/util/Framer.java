package uk.co.techblue.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Framer {

	public static byte[] nextToken(final InputStream inputStream,
			final byte[] delimiter) throws IOException {
		int nextByte;
		// If the stream has already ended, return null
		if ((nextByte = inputStream.read()) == -1)
			return null;
		ByteArrayOutputStream tokenBuffer = new ByteArrayOutputStream();
		do {
			tokenBuffer.write(nextByte);
			byte[] currentToken = tokenBuffer.toByteArray();
			if (endsWith(currentToken, delimiter)) {
				int tokenLength = currentToken.length - delimiter.length;
				byte[] token = new byte[tokenLength];
				System.arraycopy(currentToken, 0, token, 0, tokenLength);
				return token;
			}
		} while ((nextByte = inputStream.read()) != -1); // Stop on
															// end-of-stream
		return tokenBuffer.toByteArray();
	}

	// Returns true if value ends with the bytes in suffix
	private static boolean endsWith(byte[] value, byte[] suffix) {
		if (value.length < suffix.length)
			return false;
		for (int offset = 1; offset <= suffix.length; offset++)
			if (value[value.length - offset] != suffix[suffix.length - offset])
				return false;
		return true;
	}

}
