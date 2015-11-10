package com.nerbit.crypto.Padding;

import java.security.SecureRandom;

public class PaddingTest {
	public static void main(String[] args) throws Exception {
		/*
		 * int i = 254; byte b = (byte) i; System.out.println(b);
		 */

		// test padding:
		byte[] b1 = new byte[8];
		new SecureRandom().nextBytes(b1);
		byte[] pb1 = PKCSPaddingUtil.PKCS7Padding(b1, 8);
		for (int i = 0; i < pb1.length; i++) {
			System.out.print(pb1[i] + ",");
		}
		System.out.println();

		// test unpadding:
		byte[] unpb1 = PKCSPaddingUtil.PKCS7Unpadding(pb1, 8);
		for (int i = 0; i < unpb1.length; i++) {
			System.out.print(unpb1[i] + ",");
		}

	}
}
