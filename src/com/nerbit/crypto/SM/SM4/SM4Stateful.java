package com.nerbit.crypto.SM.SM4;

import java.util.Arrays;

import com.nerbit.crypto.SM.Random;
import com.nerbit.crypto.SM.Stateful;

public class SM4Stateful implements Stateful {
	byte[] key = new byte[16];
	byte[] initIV = new byte[16];
	byte[] IV_cache = new byte[16];

	static{
		System.loadLibrary("SM234");
	}

	public byte[] getKey() {
		return Arrays.copyOf(this.key, this.key.length);
	}

	private void setKey() {
		Random.setRandom(this.key);
	}

	private void setKey(byte[] key) throws Exception {
		if (key == null || key.length != 16)
			throw new Exception("error parameter: key");
		System.arraycopy(key, 0, this.key, 0, 16);
	}

	public byte[] getInitIV() {
		return Arrays.copyOf(this.initIV, this.initIV.length);
	}

	private void setInitIV() {
		Random.setRandom(this.initIV);
		refreshIV_cache();
	}

	private void setInitIV(byte[] initIV) throws Exception {
		if (initIV == null || initIV.length != 16)
			throw new Exception("errro parameter: initIV");
		System.arraycopy(initIV, 0, this.initIV, 0, 16);
	}

	private void refreshIV_cache() {
		System.arraycopy(this.initIV, 0, this.IV_cache, 0, this.initIV.length);
	}

	@Override
	public void referesh() {
		refreshIV_cache();
	}

	public SM4Stateful() {
		setKey();
		setInitIV();
		refreshIV_cache();
	}

	public SM4Stateful(byte[] key) throws Exception {
		setKey(key);
		setInitIV();
		refreshIV_cache();
	}

	public SM4Stateful(byte[] key, byte[] initIV) throws Exception {
		setKey(key);
		setInitIV(initIV);
		refreshIV_cache();
	}
	
	public SM4Stateful deepCopy() throws Exception {
		return new SM4Stateful(this.key,this.initIV);
	}
	
	public SM4Encryptor getEncryptor() throws Exception {
		return new SM4Encryptor(this.key,this.initIV);
	}
	
	public SM4Decryptor getDecryptor() throws Exception {
		return new SM4Decryptor(this.key,this.initIV);
	}

}
