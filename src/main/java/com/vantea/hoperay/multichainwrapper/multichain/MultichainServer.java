package com.vantea.hoperay.multichainwrapper.multichain;

public class MultichainServer {

	private String host;
	private String base64Auth;
	private int port;
	private String adminAddress;
	private long nodePingTimeoutMs;
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getBase64Auth() {
		return base64Auth;
	}

	public void setBase64Auth(String base64Auth) {
		this.base64Auth = base64Auth;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAdminAddress() {
		return adminAddress;
	}

	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}

	public long getNodePingTimeoutMs() {
		return nodePingTimeoutMs;
	}

	public void setNodePingTimeoutMs(long nodePingTimeoutMs) {
		this.nodePingTimeoutMs = nodePingTimeoutMs;
	}

	@Override
	public String toString() {
		return "MultichainServer [host=" + host + ", base64Auth=" + base64Auth + ", port=" + port + ", adminAddress="
				+ adminAddress + ", nodePingTimeoutMs=" + nodePingTimeoutMs + "]";
	}

}
