/**
 * Bean for "get_incoming_transactions" and "get_outgoing_transactions" output data
 */
package com.vantea.hoperay.multichainwrapper.services.beans.output;

import java.util.Set;

public class BalancesGetTransactions {

	private String type;
	private Set<BalanceTransaction> transactions;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<BalanceTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<BalanceTransaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "BalancesGetTransactions [type=" + type + ", transactions=" + transactions + "]";
	}

}
