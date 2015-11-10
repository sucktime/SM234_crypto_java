package com.nerbit.crypto.SM;

import java.security.SecureRandom;

public class Random {
	private static SecureRandom secureRandom = new SecureRandom();

	public static byte[] getRandom(int len) {
		byte[] bytes = new byte[len];
		secureRandom.nextBytes(bytes);
		return bytes;
	}

	public static void setRandom(byte[] bytes) {
		secureRandom.nextBytes(bytes);
	}
}
