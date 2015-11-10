package com.nerbit.crypto.SM;

public interface MemSafely {
	/*
	 * immediately erase the sensitive data from the memory
	 */
	void cleanMem(Object location);
}
