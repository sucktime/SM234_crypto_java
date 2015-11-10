package com.nerbit.crypto.SM.SM4;

public class SM4Decryptor extends SM4Stateful{
	

	private native byte[] sm4DecryptCBC(byte[] cipher, byte[] IV, byte[] key);
	
	public byte[] sm4DecryptCBC(byte[] cipher) throws Exception {
		if(cipher == null || cipher.length%16 != 0)
			throw new Exception("cipher cannot be null AND cipher.length%16 must be 0");
		byte[] plain = sm4DecryptCBC(cipher, IV_cache, key);
		System.arraycopy(cipher, cipher.length-16, IV_cache, 0, 16);
		return plain;
	}

	private native byte[] sm4DecryptECB(byte[] cipher, byte[] key);
	
	public byte[] sm4DecryptECB(byte[] cipher) throws Exception {
		if(cipher == null || cipher.length%16 != 0)
			throw new Exception("cipher cannot be null AND cipher.length%16 must be 0");
		return sm4DecryptECB(cipher, this.key);
	}
	
	public SM4Decryptor() {
		super();
	}
	
	public SM4Decryptor(byte[] key) throws Exception{
		super(key);
	}
	
	public SM4Decryptor(byte[] key, byte[] initIV) throws Exception{
		super(key, initIV);
	}
}
