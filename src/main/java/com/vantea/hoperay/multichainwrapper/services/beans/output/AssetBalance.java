/**
 * Bean containing asset balance info 
 */
package com.vantea.hoperay.multichainwrapper.services.beans.output;

public class AssetBalance {

	private String name;
	private long qty;
	
	public AssetBalance(String name, long qty) {
		this.name = name;
		this.qty = qty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "AssetBalance [name=" + name + ", qty=" + qty + "]";
	}

}
