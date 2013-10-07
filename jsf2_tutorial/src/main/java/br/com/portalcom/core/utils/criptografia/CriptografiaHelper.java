package br.com.portalcom.core.utils.criptografia;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografiaHelper {
	
	public static String gerarSenhaSHA256(String password){
		
		try {
			MessageDigest algoritimo = MessageDigest.getInstance("SHA-256");
			
			algoritimo.update(password.getBytes());
			
			String senhaEncriptada = new BigInteger(1, algoritimo.digest()).toString(16);
			
			return senhaEncriptada;
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return password;
	}

	
	public static void main(String[] args) {
		System.out.println(CriptografiaHelper.gerarSenhaSHA256("123456"));
	}
}

