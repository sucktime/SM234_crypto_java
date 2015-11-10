package com.nerbit.crypto.SM.SM3;

import com.nerbit.crypto.SM.Stateful;

public class SM3DigestStateful implements Stateful{
	
	private byte[] hashState;
	
	static{
		System.loadLibrary("SM234");
	}
	
	public static native byte[] SM3Hash256(byte[] msg);
	
	private native byte[] SM3Hash256Init_(byte[] msg);
	private native byte[] SM3Hash256Pending(byte[] state, byte[] msg);
	private native byte[] SM3Hash256Final(byte[]state);
	
	public void SM3Hash256Init(byte[] msg) {
		this.hashState = SM3Hash256Init_(msg);
	}
	public void SM3Hash256Pending(byte[] msg) {
		this.hashState = SM3Hash256Pending(this.hashState, msg);
	}
	public byte[] SM3Hash256Final() {
		byte[] hash = SM3Hash256Final(this.hashState);
		referesh();
		return hash;
	}

	@Override
	public void referesh() {
		this.hashState = null;
	}
	
}
