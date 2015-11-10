package com.nerbit.crypto.SM.SM4;

import com.nerbit.crypto.SM.ByteUtils;
import com.nerbit.crypto.SM.Random;

public class SM4Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SM4Stateful sm4Stateful = new SM4Stateful();
		SM4Encryptor sm4Encryptor = sm4Stateful.getEncryptor();
		SM4Decryptor sm4Decryptor = sm4Stateful.getDecryptor();
		
		//用cbc模式连续加密拆成两段的消息：
		byte[] msg1 = new byte[32];
		byte[] msg2 = new byte[32];
		Random.setRandom(msg1);
		Random.setRandom(msg2);
		System.out.println("msg:");
		ByteUtils.printByteArray(msg1);
		System.out.println("msg2");
		ByteUtils.printByteArray(msg2);
		
		byte[] cipher1 = sm4Encryptor.sm4EncryptCBC(msg1);
		byte[] plain1  = sm4Decryptor.sm4DecryptCBC(cipher1);
		byte[] cipher2 = sm4Encryptor.sm4EncryptCBC(msg2);
		byte[] plain2  = sm4Decryptor.sm4DecryptCBC(cipher2);
		sm4Encryptor.referesh();
		sm4Decryptor.referesh();
		
		System.out.println("plain1:");
		ByteUtils.printByteArray(plain1);
		System.out.println("plain2:");
		ByteUtils.printByteArray(plain2);
		
		//用ecb模式加密消息：
		byte[] msg3 = new byte[48];
		Random.setRandom(msg3);
		System.out.println("msg3:");
		ByteUtils.printByteArray(msg3);
		
		byte[] cipher3 = sm4Encryptor.sm4EncryptECB(msg3);
		byte[] plain3  = sm4Decryptor.sm4DecryptECB(cipher3);
		
		System.out.println("plain3:");
		ByteUtils.printByteArray(plain3);
		
	}

}
