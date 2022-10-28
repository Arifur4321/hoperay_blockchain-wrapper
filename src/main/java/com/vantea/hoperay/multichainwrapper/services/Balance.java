/**
 * Class for Balance related services
 */
package com.vantea.hoperay.multichainwrapper.services;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vantea.hoperay.multichainwrapper.services.beans.input.GetTransactionsBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.GetVoucherBalancesBody;
import com.vantea.hoperay.multichainwrapper.services.beans.output.AssetBalance;
import com.vantea.hoperay.multichainwrapper.services.beans.output.BalancesGetTransactions;
import com.vantea.hoperay.multichainwrapper.services.beans.output.WrapperReturn;
import com.vantea.hoperay.multichainwrapper.utils.MultichainUtils;

@RestController
@RequestMapping(path = "/balances")
public class Balance {

	private static Logger log = LogManager.getLogger(Balance.class);

	@Autowired
	private MultichainUtils multichainUtils;

	/**
	 * Retrieves balances of an address 
	 * @param body:
	 * {
	 *  "address":"1L3tiJfXcc8aoLtR2kEP1HJcVDhPUzCeWw"
     * }
	 * @return
	 */
	@RequestMapping(
			path = "/get_voucher_balances", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public WrapperReturn getVoucherBalances(@Valid @RequestBody GetVoucherBalancesBody body) {
		log.trace("----- Balance.getVoucherBalances -----");
		log.debug("BODY:  " + body);
		
		List<AssetBalance> assetBalanceList = multichainUtils.getAddressBalances(body.getAddress());
		
		WrapperReturn wr = new WrapperReturn(assetBalanceList, null, null);
		
		return wr;
	}
	
	/**
	 * Retrieves all transactions where vouchers are sent to an address
	 * @param body:
	 * {
     *  "address": "1L3tiJfXcc8aoLtR2kEP1HJcVDhPUzCeWw",
     *  "assets": null|["v1","v2"...]
     *  ]
     * }
	 * @return
	 */
	@RequestMapping(
			path = "/get_incoming_transactions",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public WrapperReturn getIncomingTransactions(@Valid @RequestBody GetTransactionsBody body) {
		log.trace("----- Balance.getIncomingTransactions -----");
		log.debug("BODY: " + body.toString());
		
		BalancesGetTransactions transactions = multichainUtils.getAddessTransaction(body.getAddress(), body.getAssets(), true);
		
		WrapperReturn wr = new WrapperReturn(transactions, null, null);
		return wr;
	}
	
	/**
	 * Retrieves all transactions where vouchers are received by an address
	 * @param body:
	 * {
     *  "address": "1L3tiJfXcc8aoLtR2kEP1HJcVDhPUzCeWw",
     *  "assets": null|["v1","v2"...]
     *  ]
     * }
	 * @return
	 */
	@RequestMapping(
			path = "/get_outgoing_transactions",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public WrapperReturn getOutgoingTransactions(@Valid @RequestBody GetTransactionsBody body) {
		log.trace("----- Balance.getIncomingTransactions -----");
		log.debug("BODY: " + body.toString());
		
		BalancesGetTransactions transactions = multichainUtils.getAddessTransaction(body.getAddress(), body.getAssets(), false);
		
		WrapperReturn wr = new WrapperReturn(transactions, null, null);
		return wr;
	}
	
}
