package com.nerbit.crypto.SM.SM2;

public class SM2PublicKey {
	private byte[] key64 = new byte[64];

	/**
	 * @return the key
	 */
	public byte[] getKey64() {
		return key64;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	private void setKey64(byte[] key64) {
		System.arraycopy(key64, 0, this.key64, 0, 64);
	}

	private SM2PublicKey() {
	};

	public SM2PublicKey(byte[] key64) throws Exception {
		super();
		if (key64 == null || key64.length != 64)
			throw new Exception("parameter error: key64");
		this.key64 = key64;
	}

	private native boolean verifyMessage(byte[] msg, byte[] signature64,byte[] pubkey64);

	private native byte[] encrtpyMessage(byte[] msg, byte[] pubkey64);

	public boolean verifyMessage(byte[] msg, byte[] signature) {
		return verifyMessage(msg, signature, this.key64);
	}
	
	public byte[] encryptMessage(byte[] msg) {
		return encrtpyMessage(msg, this.key64);
	}
}
