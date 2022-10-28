/**
 * Class for Transaction related services
 */
package com.vantea.hoperay.multichainwrapper.services;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vantea.hoperay.multichainwrapper.exceptions.WrapperException;
import com.vantea.hoperay.multichainwrapper.multichain.RestRequestBody;
import com.vantea.hoperay.multichainwrapper.services.beans.GetTransactionResult;
import com.vantea.hoperay.multichainwrapper.services.beans.input.BroadcastRawTxBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.SignRawTransactionBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.TxIdBody;
import com.vantea.hoperay.multichainwrapper.services.beans.output.IsTxConfirmed;
import com.vantea.hoperay.multichainwrapper.services.beans.output.TransferTX;
import com.vantea.hoperay.multichainwrapper.services.beans.output.WrapperReturn;
import com.vantea.hoperay.multichainwrapper.utils.MultichainUtils;

@RestController
@RequestMapping(path = "/transactions")
public class Transactions {

	private static Logger log = LogManager.getLogger(Transactions.class);
	
	@Autowired
	private MultichainUtils multichainUtils;

	@Autowired
	private WebClient webClient;

	/**
	 * Check if a transaction is confirmed and returns number of confirmations
	 * 
	 * @param body
	 * @return
	 */
	@RequestMapping(path = "/is_tx_confirmed", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public WrapperReturn isTxConfirmed(@Valid @RequestBody TxIdBody body) {
		log.trace("----- Transactions.isTxConfirmed -----");
		log.debug("Body: " + body);

		Gson gson = new Gson();

		// Call "gettransaction" and retrieve data
		RestRequestBody gettransactionRequest = new RestRequestBody();
		gettransactionRequest.setMethod("gettransaction");

		List<Object> gettransactionParams = new LinkedList<Object>();
		gettransactionParams.add(body.getTxid());
		gettransactionRequest.setParams(gettransactionParams);

		ClientResponse gettransactionResponse = webClient.post().bodyValue(gettransactionRequest).exchange().block();
		HttpStatus gettransactionHttpStatus = gettransactionResponse.statusCode();
		log.debug("HTTP Status: " + gettransactionHttpStatus.value());
		String gettransactionResponseBody = gettransactionResponse.bodyToMono(String.class).block();
		log.debug("BODY: " + gettransactionResponseBody);
		if (!gettransactionHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(gettransactionHttpStatus, gettransactionResponseBody);
		}

		// Convert into object to get data
		JsonObject fullresponse = JsonParser.parseString(gettransactionResponseBody).getAsJsonObject();
		JsonElement result = fullresponse.get("result");
		GetTransactionResult getTransactionResult = gson.fromJson(result, GetTransactionResult.class);
		log.debug("getTransactionResult: " + getTransactionResult);

		// Put "confirmations" data in generic wrapper response
		IsTxConfirmed isTxConfirmed = new IsTxConfirmed(getTransactionResult.getConfirmations());
		WrapperReturn wr = new WrapperReturn(isTxConfirmed, null, null);

		return wr;
	}

	/**
	 * Send a transaction to MultiChain to be written into blockchain
	 * 
	 * @param body
	 * @return
	 */
	@RequestMapping(path = "/broadcast_raw_tx", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String broadcastRawTx(@Valid @RequestBody BroadcastRawTxBody body) {
		log.trace("----- Transactions.broadcastRawTx -----");
		log.debug("BODY:" + body);

		// Call "sendrawtransaction" to send transfer data
		RestRequestBody sendrawtransactionRequest = new RestRequestBody();
		sendrawtransactionRequest.setMethod("sendrawtransaction");

		List<Object> sendrawtransactionParams = new LinkedList<Object>();
		sendrawtransactionParams.add(body.getRawtx());
		sendrawtransactionRequest.setParams(sendrawtransactionParams);

		ClientResponse sendrawtransactionResponse = webClient.post().bodyValue(sendrawtransactionRequest).exchange().block();
		HttpStatus sendrawtransactionHttpStatus = sendrawtransactionResponse.statusCode();
		log.debug("HTTP Status: " + sendrawtransactionHttpStatus.value());
		String sendrawtransactionResponseBody = sendrawtransactionResponse.bodyToMono(String.class).block();
		log.debug("BODY: " + sendrawtransactionResponseBody);
		if (!sendrawtransactionHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(sendrawtransactionHttpStatus, sendrawtransactionResponseBody);
		}

		return sendrawtransactionResponseBody;
	}

	/**
	 * Sign a raw transaction using a provided private key
	 * 
	 * @param body
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	@RequestMapping(
			path = "/sign_raw_tx",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public WrapperReturn signRawTx(@Valid @RequestBody SignRawTransactionBody body) throws NoSuchAlgorithmException, NoSuchProviderException {
		log.trace("----- Transaction.signRawTransaction -----");
		log.debug("BODY:" + body);

		String signedTx = null;

		try {
			signedTx = multichainUtils.signTransactionWithPrivKey(body.getRawtx(), body.getPrivate_key());
		} catch (Exception ex) {
			throw new WrapperException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}

		TransferTX signedTransaction = new TransferTX(signedTx, true);

		WrapperReturn wr = new WrapperReturn(signedTransaction, null, null);

		return wr;
	}
	
	/**
	 * Checks if a transaction is a "burn" transaction checking addresses. Returns "true" or "false"
	 * If given transaction is not asset related throws an exception.
	 * @param body - {@link TxIdBody}
	 * {
	 *  "txid":"37140761a4cbdfaa14f8d46c276213a0e73b6de7f77b9e162cdbe5cc365b393e"
	 * }
	 * @return
	 */
	@RequestMapping(
			path = "/is_tx_burn",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public WrapperReturn isTxBurn(@Valid @RequestBody TxIdBody body) {
		log.trace("----- Transfer.isTxBurn -----");
		log.debug("BODY: " + body.toString());
		
		String burnAddress = multichainUtils.getBurnAddress();
		
		// Get transaction from blockchain
		JsonObject fullTransaction = multichainUtils.getTransaction(body.getTxid(), true);
		log.debug("fullTransaction: " + fullTransaction.toString());
		
		// Check if "vout" has "assets" element and if is present check address in "scriptPubKey" with burn address taken from "getinfo"
		boolean foundAsset = false;
		boolean isBurn = false;
		JsonObject txOnly = fullTransaction.get("result").getAsJsonObject();
		JsonArray vouts = txOnly.get("vout").getAsJsonArray();
		Iterator<JsonElement> voutsIt = vouts.iterator();
		while (voutsIt.hasNext()) {
			JsonElement vout = voutsIt.next();
			log.debug("VOUT: " + vout);
			JsonElement assets = vout.getAsJsonObject().get("assets");
			if (assets != null) {
				foundAsset = true;
			}
			JsonElement addressesElement = vout.getAsJsonObject().get("scriptPubKey").getAsJsonObject().get("addresses");
			if (addressesElement != null) {
				JsonArray addresses = addressesElement.getAsJsonArray();
				Iterator<JsonElement> addressesIterator = addresses.iterator();
				while (addressesIterator.hasNext()) {
					String address = addressesIterator.next().getAsString();
					log.trace("TX[" + body.getTxid() + "] address: " + address);
					log.debug("Check " + address + " with burn address:" + burnAddress);
					if (address.equals(burnAddress)) {
						log.debug("TX[" + body.getTxid() + "] is burn transaction");
						isBurn = true;
					}
				}
			}
		}
		
		if (!foundAsset) {
			throw new WrapperException(HttpStatus.NOT_FOUND, "Transaction is not asset related");
		}
		
		WrapperReturn wr = new WrapperReturn(isBurn, null, null);
		
		return wr;
	}

}
