package com.vantea.hoperay.multichainwrapper.services.beans;

import java.util.List;

import com.vantea.hoperay.multichainwrapper.services.beans.input.MCGetTransactionBalance;

public class MCGetTransactionResult {

	private MCGetTransactionBalance balance;
	private List<String> myaddresses;
	private List<String> addresses;
	private List<Object> permissions;
	private Object issue;
	private List<Object> items;
	private List<Object> data;
	private long confirmations;
	private String blockhash;
	private long blockindex;
	private long blocktime;
	private String txid;
	private boolean valid;
	private long time;
	private long timereceived;

	public MCGetTransactionBalance getBalance() {
		return balance;
	}

	public void setBalance(MCGetTransactionBalance balance) {
		this.balance = balance;
	}

	public List<String> getMyaddresses() {
		return myaddresses;
	}

	public void setMyaddresses(List<String> myaddresses) {
		this.myaddresses = myaddresses;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public List<Object> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Object> permissions) {
		this.permissions = permissions;
	}

	public Object getIssue() {
		return issue;
	}

	public void setIssue(Object issue) {
		this.issue = issue;
	}

	public List<Object> getItems() {
		return items;
	}

	public void setItems(List<Object> items) {
		this.items = items;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
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

	@Override
	public String toString() {
		return "MCGetTransactionResult [balance=" + balance + ", myaddresses=" + myaddresses + ", addresses="
				+ addresses + ", permissions=" + permissions + ", issue=" + issue + ", items=" + items + ", data="
				+ data + ", confirmations=" + confirmations + ", blockhash=" + blockhash + ", blockindex=" + blockindex
				+ ", blocktime=" + blocktime + ", txid=" + txid + ", valid=" + valid + ", time=" + time
				+ ", timereceived=" + timereceived + ", getBalance()=" + getBalance() + ", getMyaddresses()="
				+ getMyaddresses() + ", getAddresses()=" + getAddresses() + ", getPermissions()=" + getPermissions()
				+ ", getIssue()=" + getIssue() + ", getItems()=" + getItems() + ", getData()=" + getData()
				+ ", getConfirmations()=" + getConfirmations() + ", getBlockhash()=" + getBlockhash()
				+ ", getBlockindex()=" + getBlockindex() + ", getBlocktime()=" + getBlocktime() + ", getTxid()="
				+ getTxid() + ", isValid()=" + isValid() + ", getTime()=" + getTime() + ", getTimereceived()="
				+ getTimereceived() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
