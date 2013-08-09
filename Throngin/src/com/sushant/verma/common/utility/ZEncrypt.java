package com.sushant.verma.common.utility;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sushant.verma.common.exception.BaseRuntimeException;

public final class ZEncrypt
{
	private static Logger log=LogManager.getLogger(ZEncrypt.class);
	private static ZEncrypt instance;

	public synchronized String encrypt(String plaintext) throws BaseRuntimeException
	{
		MessageDigest md = null;
		try
		{
			md = MessageDigest.getInstance("SHA"); //step 2
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new BaseRuntimeException(e.getMessage());
		}
		try
		{
			md.update(plaintext.getBytes("UTF-8")); //step 3
		}
		catch(UnsupportedEncodingException e)
		{
			throw new BaseRuntimeException(e.getMessage());
		}

		byte raw[] = md.digest(); //step 4
		String hash = (new BASE64Encoder()).encode(raw); //step 5
		return hash; //step 6
	}

	public static synchronized ZEncrypt getInstance() //step 1
	{
		if(instance == null)
		{
			instance = new ZEncrypt(); 
		} 
		return instance;
	}




	/**
	 * Blowfish Encryption and Decryption
	 * @param to_encrypt
	 * @param strkey
	 * @return
	 */

	public static String encryptBlowfish(String to_encrypt, String strkey) {
		try {
			SecretKeySpec key = new SecretKeySpec(strkey.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return new String(cipher.doFinal(to_encrypt.getBytes()));
		} catch (Exception e) { return null; }
	}
	 
	public static String decryptBlowfish(String to_decrypt, String strkey) {
	  try {
	     SecretKeySpec key = new SecretKeySpec(strkey.getBytes(), "Blowfish");
	     Cipher cipher = Cipher.getInstance("Blowfish");
	     cipher.init(Cipher.DECRYPT_MODE, key);
	     byte[] decrypted = cipher.doFinal(to_decrypt.getBytes());
	     return new String(decrypted);
	  } catch (Exception e) { return null; }
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * AES Encryption and Decryption
	 */
	
	private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = 
        new byte[] { 'T', '@', 'i', 's', 'I', '$', 'A', 'S', 'e', '2', 'r', 'e', '9', 'K', 'e', '6' };

     public static String encryptAES(String valueToEnc) throws Exception {
        Key key = generateAESKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encValue);
        return encryptedValue;
    }

	public static String decryptAES(String encryptedValue){
		String decryptedValue="0&InvalidWord";
		try{
			Key key = generateAESKey();
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedValue);
			byte[] decValue = c.doFinal(decordedValue);
			decryptedValue = new String(decValue);
			return decryptedValue;
		} catch (IllegalBlockSizeException e) {
			log.error("IllegalBlockSizeException",e);
		} catch (BadPaddingException e) {
			log.error("BadPaddingException",e);
		} catch (NoSuchAlgorithmException e) {
			log.error("NoSuchAlgorithmException",e);
		} catch (NoSuchPaddingException e) {
			log.error("NoSuchPaddingException",e);
		} catch (InvalidKeyException e) {
			log.error("InvalidKeyException",e);
		} catch (IOException e) {
			log.error("IOException",e);
		}
		return decryptedValue;
	}

    private static Key generateAESKey(){
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        // SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        // key = keyFactory.generateSecret(new DESKeySpec(keyValue));
        return key;
    }
}



