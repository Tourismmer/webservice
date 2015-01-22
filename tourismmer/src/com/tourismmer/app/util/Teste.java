package com.tourismmer.app.util;

import org.apache.commons.mail.EmailException;

import com.tourismmer.app.model.User;



public class Teste {

	public static void main(String[] args) {

		// Teste EncryptDecryptRSA
		String teste = "1";
		String s1 = EncryptDecryptRSA.encrypt(teste);
		System.out.println(s1);
		String s2 = EncryptDecryptRSA.decrypt(s1);
		System.out.println(s2);

//		String seuTexto = "taylsonmartinez.com";
//		
//		BasicTextEncryptor bte = new BasicTextEncryptor();
//		bte.setPassword("1234567");
//		 
//        //criamos uma String que recebe a senha criptografada
//        String seuTextoCriptografado = bte.encrypt(seuTexto);
//        System.out.println("Seu texto criptografado = " + seuTextoCriptografado);
// 
//        //criamos uma String que recebe a senha descriptografada
//        String seuTextoNovamenteDescriptografado = bte.decrypt(seuTextoCriptografado);
//        System.out.println("Texto descriptografado  = " + seuTextoNovamenteDescriptografado);
		
		User user = new User();
		
		user.setName("Flavio");
		user.setEmail("flavioso16@gmail.com");
		user.setId(1L);
		
		try {
			EmailService.sendEmailPassRecover(user);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		

	}

}
