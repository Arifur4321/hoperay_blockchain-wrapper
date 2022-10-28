/**
 * Detail for transaction
 */
package com.vantea.hoperay.multichainwrapper.services.beans.output;

import java.util.Set;

public class BalanceTransaction {

	private String txid;
	private String sender;
	private String receiver;
	private Set<AssetBalance> assets;

	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Set<AssetBalance> getAssets() {
		return assets;
	}

	public void setAssets(Set<AssetBalance> assets) {
		this.assets = assets;
	}

	@Override
	public String toString() {
		return "BalanceTransaction [txid=" + txid + ", sender=" + sender + ", receiver=" + receiver + ", assets="
				+ assets + "]";
	}

}
