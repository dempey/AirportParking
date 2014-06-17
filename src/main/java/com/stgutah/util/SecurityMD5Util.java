package com.stgutah.util;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

@Configuration
public class SecurityMD5Util
{
	private static Logger logger = Logger.getLogger("util");

	public String getMD5Value(String password)
	{
		logger.info("Generating hash code for new password.");
		
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		
		String hashedPassword = encoder.encodePassword(password, null);
		
		return hashedPassword;
	}

}
