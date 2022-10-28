/**
 * Bean from "isalive" status service
 */
package com.vantea.hoperay.multichainwrapper.services.beans.output;

public class IsAlive {

	private long ts;
	private String status;
	
	public IsAlive(long ts, String status) {
		this.ts = ts;
		this.status = status;
	}

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "IsAlive [ts=" + ts + ", status=" + status + "]";
	}

}
