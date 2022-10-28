package com.vantea.hoperay.multichainwrapper.services.beans.input;

public class NewKeyPairBody {


	@Override
	public String toString() {
		return "NewKeyPairBody [seed=" + seed + "]";
	}

	private String seed;
	
	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

 

}
