package com.nerbit.crypto.SM.SM2;

public class SM2Test {

	public static void main(String[] args) throws Exception {
		byte[] privateKey32 = new byte[32];
		for (int i = 0; i < 32; i++)
			privateKey32[i] = (byte) i;
		SM2Key sm2Key = new SM2Key(privateKey32);
		
		System.out.println(sm2Key.getPrivateKey32().length);
		com.nerbit.crypto.SM.ByteUtils.printByteArray(sm2Key.getPrivateKey32());
		System.out.println(sm2Key.getPublicKey().getKey64().length);
		com.nerbit.crypto.SM.ByteUtils.printByteArray(sm2Key.getPublicKey().getKey64());
		System.out.println();
		
		byte[] msg = new byte[100];
		for(int i=0;i<100;i++)
			msg[i] = (byte)i;
		byte[] sig64 = sm2Key.signMessage(msg);
		System.out.println(sig64.length);
		com.nerbit.crypto.SM.ByteUtils.printByteArray(sig64);
		
		SM2PublicKey sm2PublicKey = sm2Key.getPublicKey();
		boolean re = sm2PublicKey.verifyMessage(msg, sig64);
		System.out.println(re);
		
		System.out.println("before encrypt: msg:");
		System.out.println(msg.length);
		com.nerbit.crypto.SM.ByteUtils.printByteArray(msg);
		byte[] cipher = sm2PublicKey.encryptMessage(msg);
		System.out.println("cipher:");
		System.out.println(cipher.length);
		com.nerbit.crypto.SM.ByteUtils.printByteArray(cipher);
		byte[] plain = sm2Key.decryptMessage(cipher);
		System.out.println("after decrpt: msg:");
		System.out.println(plain.length);
		com.nerbit.crypto.SM.ByteUtils.printByteArray(plain);
	}

}
