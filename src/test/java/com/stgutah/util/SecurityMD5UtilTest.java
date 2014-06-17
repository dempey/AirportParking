package com.stgutah.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SecurityMD5UtilTest
{
	
	@Test
	public static void testGetMD5Value()
	{
		SecurityMD5Util securityUtil = new SecurityMD5Util();
		
		String md5Hash = securityUtil.getMD5Value("TestPassword");
		
		Assert.assertEquals(md5Hash, "23fd44228071730e3457dc5de887b3ae");
	}
	
}