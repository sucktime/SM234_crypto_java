package com.nerbit.crypto.SM;

import java.util.Arrays;

public class ByteUtils {
	public static final byte[] zero16 = new byte[16];

	public static void printByteArray(byte[] bytes) {
		System.out.print(bytes.length + ":");
		for (int i = 0; i < bytes.length; i++)
			System.out.print(unsignedByte(bytes[i]) + "-");
		System.out.println();
	}

	public static int unsignedByte(byte b) {
		return (0x0FF & b);
		//return Byte.toUnsignedInt(b);
	}

	static char[] hextTable = new char[] { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	//static byte[] reversalHexTable = new byte[]{};


	public static char[] byte2hex(byte b) {
		char[] bytehex = new char[2];
		bytehex[0] = hextTable[(0x0F0 & b)>>4];
		bytehex[1] = hextTable[0x00F & b];
		return bytehex;
	}
	
	public static char[] byteSequence2HexSequence(byte[] bseq) throws Exception {
		if(bseq == null)
			throw new Exception("null parameter");
		int len = bseq.length;
		char[] cseq = new char[2*len];
		
		for(int i=0; i<len; i++){
			char[] c = byte2hex(bseq[i]);
			cseq[2*i] = c[0];
			cseq[2*i+1] = c[1];
		}
		
		return cseq;
	}

	public static byte hex2byte(char[] hexbyte) throws Exception {
		if(hexbyte == null || hexbyte.length != 2)
			throw new Exception("para null ,or para length != 2");
		byte b1 = getHexValue(hexbyte[0]);
		byte b2 = getHexValue(hexbyte[1]);
		
		return (byte) ((b1<<4)+b2);
	}
	
	public static byte getHexValue(char c) throws Exception {
		byte b = 0;
		if(c>='0' && c <= '9'){
			b = (byte) (0x0F & (c-'0'));
		}
		else if(c>='A' && c<='F'){
			b = (byte)(0x0F & (c - 'A' + 10));
		}
		else {
			throw new Exception("value must be [0-9,A-F]");
		}
		return b;
	}
	
	public static byte[] hexSequence2ByteSequence(char[] cseq) throws Exception{
		if(cseq == null || cseq.length%2 != 0)
			throw new Exception("null parameter,or invalid parameter.length");
		
		int len = cseq.length/2;
		byte[] bseq = new byte[len];
		
		for(int i=0; i<len; i++){
			bseq[i] = hex2byte(new char[]{cseq[2*i],cseq[2*i+1]});
		}
		return bseq;
	}
	
	public static int byteSequenceCompare(byte[] b1, byte[] b2) throws Exception {
		if(b1 == null || b1 == null)
			throw new Exception("null parameter");
		int len1 = b1.length, len2 = b2.length;
		int i = 0;
		int v1,v2,v3;
		while(i<len1 && i<len2){
			v1 = 0x0FF&b1[i];
			v2 = 0x0FF&b2[i];
			v3 = v1-v2;
			if(v3>0) return 1;
			if(v3<0) return -1;
		}
		v1 = len1>i?1:0;
		v2 = len2>i?1:0;
		return v1-v2;
	}
}
