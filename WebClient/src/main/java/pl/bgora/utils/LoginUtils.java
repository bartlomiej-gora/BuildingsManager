/**
 * 
 */
package pl.bgora.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public final class LoginUtils {


	private LoginUtils() {
	}


	public static String getHashPassword(String passwd)
			throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(passwd.getBytes());
		byte[] result = digest.digest();
		return new String(result, Charset.forName("UTF-8"));
	}
}
