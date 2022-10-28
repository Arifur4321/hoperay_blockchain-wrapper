/**
 * Bean containint a raw transaction 
 */
package com.vantea.hoperay.multichainwrapper.services.beans.output;

public class TransferTX {

	private String raw_tx;
	private boolean is_signed;

	public TransferTX(String raw_tx, boolean is_signed) {
		this.raw_tx = raw_tx;
		this.setIs_signed(is_signed);
	}

	public String getRaw_tx() {
		return raw_tx;
	}

	public void setRaw_tx(String raw_tx) {
		this.raw_tx = raw_tx;
	}

	public boolean isIs_signed() {
		return is_signed;
	}

	public void setIs_signed(boolean is_signed) {
		this.is_signed = is_signed;
	}

	@Override
	public String toString() {
		return "TransferTX [raw_tx=" + raw_tx + ", is_signed=" + is_signed + "]";
	}

}
