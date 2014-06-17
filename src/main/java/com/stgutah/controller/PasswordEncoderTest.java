package com.stgutah.controller;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordEncoderTest
{
	public static void main(String[] args)
	{
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		
		String hashedPass = encoder.encodePassword("brianjohnson", null);
		
		System.out.println(hashedPass);
	}

}