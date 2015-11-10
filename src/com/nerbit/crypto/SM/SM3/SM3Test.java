package com.nerbit.crypto.SM.SM3;

import com.nerbit.crypto.SM.ByteUtils;
import com.nerbit.crypto.SM.Random;

@SuppressWarnings("unused")
public class SM3Test {
	public static void main(String[] args) {
		SM3DigestStateful sm3DigestStateful = new SM3DigestStateful();
		
		byte[] msg1 = new byte[60];
		//Random.setRandom(msg1);
		for(int i=0; i<msg1.length; i++)
			msg1[i] = (byte)i;
		System.out.println("msg1:");
		ByteUtils.printByteArray(msg1);
		
		byte[] hash1 = SM3DigestStateful.SM3Hash256(msg1);
		System.out.println("hash1:");
		ByteUtils.printByteArray(hash1);
		
		byte[] msg2 = new byte[6];
		for(int i=0; i<msg2.length; i++){
			msg2[i] = (byte) (60+i);
		}
		//Random.setRandom(msg2);
		//System.arraycopy(new byte[]{'a','b','c'},0 , msg2, 0, 3);
		System.out.println("msg2:");
		ByteUtils.printByteArray(msg2);
		
		sm3DigestStateful.SM3Hash256Init(msg1);
		sm3DigestStateful.SM3Hash256Pending(msg2);
		byte[] hashof2 = sm3DigestStateful.SM3Hash256Final();
		System.out.println("hash of 2:");
		ByteUtils.printByteArray(hashof2);
	}
}
