package com.nerbit.crypto.SM.SM2;

public class SM2Key {
	private SM2PublicKey publicKey;
	private byte[] privatekey32 = new byte[32];

	public SM2Key() throws Exception {
		byte[] keyPair = generateSM2KeyPair();
		if (keyPair == null || keyPair.length != 96)
			throw new Exception("error key pair generated!");
		System.arraycopy(keyPair, 0, privatekey32, 0, 32);
		byte[] pubkey = new byte[64];
		System.arraycopy(keyPair, 32, pubkey, 0, 64);
		this.publicKey = new SM2PublicKey(pubkey);
	}

	public SM2Key(byte[] privateKey32) throws Exception {
		if (privateKey32 == null || privateKey32.length != 32)
			throw new Exception("error parameter: privateKey32");
		System.arraycopy(privateKey32, 0, privatekey32, 0, 32);
		byte[] pubkey = generatePubicKey(privateKey32);
		if (pubkey == null || pubkey.length != 64)
			throw new Exception("error pubkey generated!");
		this.publicKey = new SM2PublicKey(pubkey);
	}

	static {
		System.loadLibrary("SM234");
	}

	public static native byte[] generatePubicKey(byte[] privateKey);

	public static native byte[] generateSM2KeyPair();

	private native byte[] signMessage(byte[] msg, byte[] prikey32);

	private native byte[] decryptMessage(byte[] cipher, byte[] prikey32);
	
	public byte[] signMessage(byte[] msg) {
		return signMessage(msg, this.privatekey32);
	}
	
	public byte[] decryptMessage(byte[] cipher) {
		return decryptMessage(cipher, this.privatekey32);
	}

	public SM2PublicKey getPublicKey() {
		return this.publicKey;
	}

	public byte[] getPrivateKey32() {
		return this.privatekey32;
	}
}
