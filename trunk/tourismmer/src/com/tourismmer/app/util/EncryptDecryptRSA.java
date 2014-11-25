package com.tourismmer.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tourismmer.app.constants.ViewConstants;

public class EncryptDecryptRSA {
	
	private static final String ALGORITHM = "RSA"; 
	
	private static final String PATH_PRIVATE_KEY = "./keys/private.key";
	
	private static final String PATH_PUBLIC_KEY = "./keys/public.key";
	
	private static void createKeys()
	{
		try { 
			final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
			keyGen.initialize(512); 
			final KeyPair key = keyGen.generateKeyPair(); 
			File privateKeyFile = new File(PATH_PRIVATE_KEY); 
			File publicKeyFile = new File(PATH_PUBLIC_KEY); 
			
			// Create the files the key private and key public 
			if (privateKeyFile.getParentFile() != null) { 
				privateKeyFile.getParentFile().mkdirs(); 
			} 
			privateKeyFile.createNewFile(); 
			
			if (publicKeyFile.getParentFile() != null) { 
				publicKeyFile.getParentFile().mkdirs(); 
			} 
			publicKeyFile.createNewFile(); 
			
			// Save key public
			ObjectOutputStream chavePublicaOS = new ObjectOutputStream( new FileOutputStream(publicKeyFile)); 
			chavePublicaOS.writeObject(key.getPublic()); 
			chavePublicaOS.close(); 
			
			// Save key private
			ObjectOutputStream chavePrivadaOS = new ObjectOutputStream( new FileOutputStream(privateKeyFile)); 
			chavePrivadaOS.writeObject(key.getPrivate()); 
			chavePrivadaOS.close(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 
	
	/** 
	 * Verifica se o par de chaves Pública e Privada já foram geradas. 
	 */ 
	private static boolean checkKeyExist() { 
		File privateKey = new File(PATH_PRIVATE_KEY); 
		File publicKey = new File(PATH_PUBLIC_KEY); 
		
		if (privateKey.exists() && publicKey.exists()) { 
			return true; 
		} 
		
		return false; 
	} 
	
	/** 
	 * Criptografa o texto puro usando chave pública. 
	 */ 
	public static String encrypt(String text) { 
		byte[] cipherText = null; 
		
		try { 
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			// encrypt the text with public key
			cipher.init(Cipher.ENCRYPT_MODE, getPublicKey()); 
			cipherText = cipher.doFinal(text.getBytes()); 
			return toHex(cipherText); 
		} catch (Exception e) { 
			Log log = LogFactory.getLog(EncryptDecryptRSA.class);
			log.error(e);
		} 
		
		return ViewConstants.EMPYT;
	} 
	
	/** 
	 * Decriptografa o texto puro usando chave privada. 
	 */ 
	public static String decrypt(String text) { 
		byte[] dectyptedText = null; 
		
		try { 
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			
			// decrypt the text with private key 
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey()); 
			dectyptedText = cipher.doFinal(fromHex(text)); 
			return new String(dectyptedText); 
		} catch (Exception e) { 
			Log log = LogFactory.getLog(EncryptDecryptRSA.class);
			log.error(e);
		} 
		return ViewConstants.EMPYT;
	}
	
	private static PublicKey getPublicKey() {
		PublicKey publicKey = null;
		
		// check if key exist
		if (!checkKeyExist()) { 
			createKeys();
		}
		try {
			ObjectInputStream inputStream = null; 
			inputStream = new ObjectInputStream(new FileInputStream(PATH_PUBLIC_KEY)); 
			publicKey = (PublicKey) inputStream.readObject(); 
			inputStream.close();
		} catch (Exception e) { 
			Log log = LogFactory.getLog(EncryptDecryptRSA.class);
			log.error(e);
		}
		
		return publicKey;
	}
	
	private static PrivateKey getPrivateKey() {
		PrivateKey privateKey = null;
		
		// check if key exist
		if (!checkKeyExist()) { 
			createKeys();
		}
		try {
			ObjectInputStream inputStream = null; 
			// Criptografa a Mensagem usando a Chave Pública 
			inputStream = new ObjectInputStream(new FileInputStream(PATH_PRIVATE_KEY)); 
			privateKey = (PrivateKey) inputStream.readObject(); 
			inputStream.close();
		} catch (Exception e) { 
			Log log = LogFactory.getLog(EncryptDecryptRSA.class);
			log.error(e);
		}
		
		return privateKey;
	}
	
	private static String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0) 
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }
	
	private static byte[] fromHex(String hex)
    {
        byte[] binary = new byte[hex.length() / 2];
        for(int i = 0; i < binary.length; i++)
        {
            binary[i] = (byte)Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
        }
        return binary;
    }
}


