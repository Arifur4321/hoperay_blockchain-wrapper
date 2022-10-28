package com.vantea.hoperay.multichainwrapper.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vantea.hoperay.multichainwrapper.services.beans.output.KeyPairAndAddress;

public class AddressGen implements Runnable {

	private static Logger log = LogManager.getLogger(AddressGen.class);

	private KeyPairAndAddress keyPairAndAddress;
	private String seed;

	public AddressGen(KeyPairAndAddress keyPairAndAddress, String seed) {
		this.setKeyPairAndAddress(keyPairAndAddress);
		this.seed = seed;
	}

	@Override
	public void run() {
		try {
			setKeyPairAndAddress(Utils.generateKeyPair(seed));
		} catch (Exception e) {
			log.error("ERROR GENERATING KEYS!", e);
		}
	}

	public KeyPairAndAddress getKeyPairAndAddress() {
		return keyPairAndAddress;
	}

	public void setKeyPairAndAddress(KeyPairAndAddress keyPairAndAddress) {
		this.keyPairAndAddress = keyPairAndAddress;
	}
}
