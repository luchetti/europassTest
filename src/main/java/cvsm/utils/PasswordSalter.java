package cvsm.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class PasswordSalter {

	public static String saltPassword(String password, String encode){
		
		
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(encode);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		
		digest.update(password.getBytes(StandardCharsets.UTF_8));
		
		return Base64.encodeBase64String(digest.digest()); 
	}
}
