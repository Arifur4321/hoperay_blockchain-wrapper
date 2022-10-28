/**
 * Bean wrapping transaction balance
 */
package com.vantea.hoperay.multichainwrapper.services.beans.input;

import java.util.List;

import com.vantea.hoperay.multichainwrapper.services.beans.output.AssetBalance;

public class MCGetTransactionBalance {

	private long amount;
	private List<AssetBalance> assets;

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public List<AssetBalance> getAssets() {
		return assets;
	}

	public void setAssets(List<AssetBalance> assets) {
		this.assets = assets;
	}

	@Override
	public String toString() {
		return "MCGetTransactionBalance [amount=" + amount + ", assets=" + assets + "]";
	}

}
