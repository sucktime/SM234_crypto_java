package com.nerbit.crypto.SM;
/*
 * Author : Jiang Guofeng 2013 @ dcs
 * version: 1.0-SNAPSHOT
 * date   : 2015-11-9
 */
public class SMTest {
	public static void main(String[] args) throws Exception {
		char[] hex = new char[]{'F','F'};
		System.out.println(ByteUtils.unsignedByte(ByteUtils.hex2byte(hex)));
		
		char[] charseq = "12E34A43C2".toCharArray();
		byte[] byteseq  = ByteUtils.hexSequence2ByteSequence(charseq);
		
		ByteUtils.printByteArray(byteseq);
		
		char[] cseq = ByteUtils.byteSequence2HexSequence(byteseq);
		System.out.println(String.valueOf(cseq));
	
	}
}
