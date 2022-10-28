package com.vantea.hoperay.multichainwrapper.services.beans.output;

public class IsTxConfirmed {

	private long confirmations;

	public IsTxConfirmed(long confirmations) {
		this.confirmations = confirmations;
	}

	public long getConfirmations() {
		return confirmations;
	}

	public void setConfirmations(long confirmations) {
		this.confirmations = confirmations;
	}

	@Override
	public String toString() {
		return "IsTxConfirmed [confirmations=" + confirmations + "]";
	}

}
