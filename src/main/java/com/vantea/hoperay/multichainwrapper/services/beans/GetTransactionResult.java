package com.vantea.hoperay.multichainwrapper.services.beans;

import java.util.List;

public class GetTransactionResult {

	private long amount;
	private long confirmations;
	private String blockhash;
	private long blockindex;
	private long blocktime;
	private String txid;
	private List<Object> walletconflicts;
	private boolean valid;
	private long time;
	private long timereceived;
	private List<Object> details;
	private String hex;

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getConfirmations() {
		return confirmations;
	}

	public void setConfirmations(long confirmations) {
		this.confirmations = confirmations;
	}

	public String getBlockhash() {
		return blockhash;
	}

	public void setBlockhash(String blockhash) {
		this.blockhash = blockhash;
	}

	public long getBlockindex() {
		return blockindex;
	}

	public void setBlockindex(long blockindex) {
		this.blockindex = blockindex;
	}

	public long getBlocktime() {
		return blocktime;
	}

	public void setBlocktime(long blocktime) {
		this.blocktime = blocktime;
	}

	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public List<Object> getWalletconflicts() {
		return walletconflicts;
	}

	public void setWalletconflicts(List<Object> walletconflicts) {
		this.walletconflicts = walletconflicts;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getTimereceived() {
		return timereceived;
	}

	public void setTimereceived(long timereceived) {
		this.timereceived = timereceived;
	}

	public List<Object> getDetails() {
		return details;
	}

	public void setDetails(List<Object> details) {
		this.details = details;
	}

	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}

	@Override
	public String toString() {
		return "GetTransactionResult [amount=" + amount + ", confirmations=" + confirmations + ", blockhash="
				+ blockhash + ", blockindex=" + blockindex + ", blocktime=" + blocktime + ", txid=" + txid
				+ ", walletconflicts=" + walletconflicts + ", valid=" + valid + ", time=" + time + ", timereceived="
				+ timereceived + ", details=" + details + ", hex=" + hex + "]";
	}

}
