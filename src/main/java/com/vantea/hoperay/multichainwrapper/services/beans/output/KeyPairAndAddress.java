/**
 * Bean containing keys
 */
package com.vantea.hoperay.multichainwrapper.services.beans.output;

public class KeyPairAndAddress {

	private String privateKey;
	private String publicKey;
	private String address;

	public KeyPairAndAddress(String privateKey, String publicKey, String address) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
		this.address = address;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "KeyPairAndAddress [privateKey=" + "*****" + ", publicKey=" + publicKey + ", address=" + address + "]";
	}

}
