package com.vantea.hoperay.multichainwrapper.services.beans;

public class GetInfoResult {

	private String version;
	private int nodeversion;
	private String edition;
	private String protocolversion;
	private String chainname;
	private String description;
	private String protocol;
	private int port;
	private int setupblocks;
	private String nodeaddress;
	private String burnaddress;
	private String incomingpaused;
	private String miningpaused;
	private String offchainpaused;
	private int walletversion;
	private int balance;
	private int walletdbversion;
	private boolean reindex;
	private int blocks;
	private int timeoffset;
	private int connections;
	private String proxy;
	private long difficulty;
	private String testnet;
	private long keypoololdest;
	private int keypoolsize;
	private int paytxfee;
	private int relayfee;
	private String errors;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getNodeversion() {
		return nodeversion;
	}

	public void setNodeversion(int nodeversion) {
		this.nodeversion = nodeversion;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getProtocolversion() {
		return protocolversion;
	}

	public void setProtocolversion(String protocolversion) {
		this.protocolversion = protocolversion;
	}

	public String getChainname() {
		return chainname;
	}

	public void setChainname(String chainname) {
		this.chainname = chainname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getSetupblocks() {
		return setupblocks;
	}

	public void setSetupblocks(int setupblocks) {
		this.setupblocks = setupblocks;
	}

	public String getNodeaddress() {
		return nodeaddress;
	}

	public void setNodeaddress(String nodeaddress) {
		this.nodeaddress = nodeaddress;
	}

	public String getBurnaddress() {
		return burnaddress;
	}

	public void setBurnaddress(String burnaddress) {
		this.burnaddress = burnaddress;
	}

	public String getIncomingpaused() {
		return incomingpaused;
	}

	public void setIncomingpaused(String incomingpaused) {
		this.incomingpaused = incomingpaused;
	}

	public String getMiningpaused() {
		return miningpaused;
	}

	public void setMiningpaused(String miningpaused) {
		this.miningpaused = miningpaused;
	}

	public String getOffchainpaused() {
		return offchainpaused;
	}

	public void setOffchainpaused(String offchainpaused) {
		this.offchainpaused = offchainpaused;
	}

	public int getWalletversion() {
		return walletversion;
	}

	public void setWalletversion(int walletversion) {
		this.walletversion = walletversion;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getWalletdbversion() {
		return walletdbversion;
	}

	public void setWalletdbversion(int walletdbversion) {
		this.walletdbversion = walletdbversion;
	}

	public boolean isReindex() {
		return reindex;
	}

	public void setReindex(boolean reindex) {
		this.reindex = reindex;
	}

	public int getBlocks() {
		return blocks;
	}

	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}

	public int getTimeoffset() {
		return timeoffset;
	}

	public void setTimeoffset(int timeoffset) {
		this.timeoffset = timeoffset;
	}

	public int getConnections() {
		return connections;
	}

	public void setConnections(int connections) {
		this.connections = connections;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public long getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(long difficulty) {
		this.difficulty = difficulty;
	}

	public String getTestnet() {
		return testnet;
	}

	public void setTestnet(String testnet) {
		this.testnet = testnet;
	}

	public long getKeypoololdest() {
		return keypoololdest;
	}

	public void setKeypoololdest(long keypoololdest) {
		this.keypoololdest = keypoololdest;
	}

	public int getKeypoolsize() {
		return keypoolsize;
	}

	public void setKeypoolsize(int keypoolsize) {
		this.keypoolsize = keypoolsize;
	}

	public int getPaytxfee() {
		return paytxfee;
	}

	public void setPaytxfee(int paytxfee) {
		this.paytxfee = paytxfee;
	}

	public int getRelayfee() {
		return relayfee;
	}

	public void setRelayfee(int relayfee) {
		this.relayfee = relayfee;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "GetInfoResult [version=" + version + ", nodeversion=" + nodeversion + ", edition=" + edition
				+ ", protocolversion=" + protocolversion + ", chainname=" + chainname + ", description=" + description
				+ ", protocol=" + protocol + ", port=" + port + ", setupblocks=" + setupblocks + ", nodeaddress="
				+ nodeaddress + ", burnaddress=" + burnaddress + ", incomingpaused=" + incomingpaused
				+ ", miningpaused=" + miningpaused + ", offchainpaused=" + offchainpaused + ", walletversion="
				+ walletversion + ", balance=" + balance + ", walletdbversion=" + walletdbversion + ", reindex="
				+ reindex + ", blocks=" + blocks + ", timeoffset=" + timeoffset + ", connections=" + connections
				+ ", proxy=" + proxy + ", difficulty=" + difficulty + ", testnet=" + testnet + ", keypoololdest="
				+ keypoololdest + ", keypoolsize=" + keypoolsize + ", paytxfee=" + paytxfee + ", relayfee=" + relayfee
				+ ", errors=" + errors + "]";
	}

}
