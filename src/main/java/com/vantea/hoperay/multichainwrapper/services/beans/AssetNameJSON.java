package com.vantea.hoperay.multichainwrapper.services.beans;

public class AssetNameJSON {

	private String name;


	private Boolean open;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	@Override
	public String toString() {
		return "AssetNameJSON [name=" + name + ", open=" + open + "]";
	}
 
}
