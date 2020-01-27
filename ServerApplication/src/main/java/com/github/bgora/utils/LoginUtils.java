/**
 * 
 */
package com.github.bgora.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Klasa narz�dziowa dla serwisu logowania.
 * 
 * @author Bart�omiej G�ra
 * 
 */
public final class LoginUtils {

	/**
	 * Kontruktor. Poniewa� klasa jest narz�dziowa, to kontruktor powinien by�
	 * prywatny, a metody narz�dziowe statyczne.
	 */
	private LoginUtils() {
	}

	/**
	 * Zwraca hash md5 podanego has�a.
	 * 
	 * W bazie danych zapisany b�dzie hash tego stringa. Po sieci nale�y
	 * przesy�a� tak�e hash has�a.
	 * 
	 * @param passwd
	 *            has�o w postaci jawnej
	 * @return has�o w postaci skr�tu md5
	 * @throws NoSuchAlgorithmException
	 *             Je�li nie mamy algorytmu szyfrowania hase�.
	 */
	public static String getHashPassword(String passwd)
			throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(passwd.getBytes());
		byte[] result = digest.digest();
		return new String(result, Charset.forName("UTF-8"));
	}
}
