package com.wujiabo.opensource.feather.test;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class CreateAdminUser {
	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private static String algorithmName = "md5";
	private static int hashIterations = 2;

	public static void main(String[] args) {
		String userName = "admin";
		String password = "123456";
		String salt = randomNumberGenerator.nextBytes().toHex();
		String newPassword = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(userName + salt),
				hashIterations).toHex();
		
		System.out.println(salt);
		System.out.println(newPassword);
	}
}
