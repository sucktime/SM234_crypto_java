package com.nerbit.crypto.Padding;

public class PKCSPaddingUtil {

	public static byte[] PKCS7Padding(byte[] content, int blockSize)
			throws Exception {
		if (content == null || blockSize < 8)
			throw new Exception("parameter error");

		int len = content.length;
		int remain = len % blockSize;
		int NUM = blockSize - remain;

		int Len = blockSize * (len / blockSize + 1);
		byte[] padded = new byte[Len];
		System.arraycopy(content, 0, padded, 0, len);
		for (int i = 0; i < NUM; i++)
			padded[len + i] = (byte) NUM;

		return padded;
	}

	public static byte[] PKCS5Padding(byte[] content) throws Exception {
		return PKCS7Padding(content, 8);
	}

	public static byte[] PKCS7Unpadding(byte[] content, int blockSize)
			throws Exception {
		if (blockSize < 8 || content == null || content.length % blockSize != 0)
			throw new Exception("parameter error");
		return PKCS7Unpadding(content);
	}

	public static byte[] PKCS7Unpadding(byte[] content) throws Exception {
		if (content == null || content.length < 8)
			throw new Exception("parameter error");

		int len = content.length;
		int NUM = content[len - 1];
		if (NUM > len)
			throw new Exception("invalid padding");
		for (int i = 0; i < NUM; i++)
			if (content[len - i - 1] != NUM)
				throw new Exception("invalid padding");
		byte[] unpadded = new byte[len - NUM];
		System.arraycopy(content, 0, unpadded, 0, len - NUM);
		return unpadded;
	}

	public static byte[] PKCS5Unpadding(byte[] content) throws Exception {
		return PKCS7Unpadding(content, 8);
	}
}
