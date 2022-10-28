/**
 * Utility Class for Multichain Calls
 */
package com.vantea.hoperay.multichainwrapper.utils;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ECKey.ECDSASignature;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vantea.hoperay.multichainwrapper.exceptions.WrapperException;
import com.vantea.hoperay.multichainwrapper.multichain.RestRequestBody;
import com.vantea.hoperay.multichainwrapper.services.beans.MCGetTransactionResult;
import com.vantea.hoperay.multichainwrapper.services.beans.MCListpermissions;
import com.vantea.hoperay.multichainwrapper.services.beans.input.Roles;
import com.vantea.hoperay.multichainwrapper.services.beans.input.VoucherQuantity;
import com.vantea.hoperay.multichainwrapper.services.beans.output.AssetBalance;
import com.vantea.hoperay.multichainwrapper.services.beans.output.BalanceTransaction;
import com.vantea.hoperay.multichainwrapper.services.beans.output.BalancesGetTransactions;

public class MultichainUtils {
	
	@Autowired
	private WebClient webClient;
	
	private String burnAddress;
	
	public MultichainUtils() {
		this.burnAddress = null;
	}
	
	/**
	 * Check if a role can send voucher to another. Allowed are (from -> to): -
	 * COMPANY -> EMPLOYEE - EMPLOYEE -> MERCHANT - MERCHANT -> Burn Address
	 * 
	 * @param roleFrom
	 * @param roleTo
	 * @return
	 */
	public boolean canSendByRole(Roles roleFrom, Roles roleTo) {
		boolean canSend = false;
		switch (roleFrom) {
		case COMPANY:
			if (roleTo.getRole().equals(Roles.EMPLOYEE.getRole())) {
				canSend = true;
			}
			break;
		case EMPLOYEE:
			if (roleTo.getRole().equals(Roles.MERCHANT.getRole())) {
				canSend = true;
			}
			break;
		case MERCHANT: // Merchant can send only to burn address so always returns false
		default:
			break;
		}
		return canSend;
	}
	
	/**
	 * Builds transfer param to send to Multichain in format
	 * {
     * 	"destination_address": {
     *  	"voucher1": quantity1,
     *  	"voucher2": quantity2
     *  	...
     *  }
     * }
	 * @param to
	 * @param qty
	 * @param doExpirationCheck
	 * @return
	 */
	public JsonObject createParamForVoucherTransfer(String to, List<VoucherQuantity> qty, boolean doExpirationCheck) {
		JsonObject sendData = new JsonObject();
		JsonObject voucherData = new JsonObject();
		for (VoucherQuantity voucherQuantity : qty) {
			String voucherName = voucherQuantity.getName();
			
			// Checking voucher expiration (not needed by "burn" transaction)
			if (doExpirationCheck) {
				if (isAssetExpired(voucherName)) {
					throw new WrapperException(HttpStatus.CONFLICT, "Voucher " + voucherName + " has expired");
				}
			}
			
			long voucherQty = voucherQuantity.getQty();
			voucherData.addProperty(voucherName, voucherQty);
		}
		sendData.add(to, voucherData);
		return sendData;
	}
	
	/* For Inplace Data passing to multichain 
	
	public JsonObject InplaceDataTransfer(String to) {
		JsonObject sendData = new JsonObject();
		JsonObject voucherData = new JsonObject();
		for (VoucherQuantity voucherQuantity : qty) {
			String voucherName = voucherQuantity.getName();
			
			// Checking voucher expiration (not needed by "burn" transaction)
			if (doExpirationCheck) {
				if (isAssetExpired(voucherName)) {
					throw new WrapperException(HttpStatus.CONFLICT, "Voucher " + voucherName + " has expired");
				}
			}
			
			long voucherQty = voucherQuantity.getQty();
			voucherData.addProperty(voucherName, voucherQty);
		}
		sendData.add(to, voucherData);
		return sendData;
	}
	 */
	
	/**
	 * Get a specific attribute in the result body of "getinfo" JSON-RPC API
	 * @param elementName
	 * @return
	 */
	public JsonElement retrieveAttributeFromGetInfo(String elementName) {
		RestRequestBody getInfoRequestBody = new RestRequestBody();
		getInfoRequestBody.setMethod("getinfo");
		List<Object> getIntoParams = new LinkedList<Object>();
		getInfoRequestBody.setParams(getIntoParams);
		ClientResponse getInfoResponse = webClient.post().bodyValue(getInfoRequestBody).exchange().block();
		HttpStatus getInfoHttpStatus = getInfoResponse.statusCode();
		String getInfoBody = getInfoResponse.bodyToMono(String.class).block();
		if (!getInfoHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(getInfoHttpStatus, getInfoBody);
		}
		JsonObject fullresponse = JsonParser.parseString(getInfoBody).getAsJsonObject();
		JsonObject response = fullresponse.get("result").getAsJsonObject();
		return response.get(elementName);
	}

	/**
	 * Get address role making a JSON-RPC call
	 * @param address
	 * @return
	 */
	public String getRoleForAddress(String address) {
		Gson gson = new Gson();
		RestRequestBody getAddressFromRole = new RestRequestBody();
		getAddressFromRole.setMethod("listpermissions");
		List<Object> params = new LinkedList<Object>();
		params.add("low1,low2,low3");
		params.add(address);
		getAddressFromRole.setParams(params);
		ClientResponse getAddressFromRoleResponse = webClient.post().bodyValue(getAddressFromRole).exchange().block();
		HttpStatus getAddressFromRoleHttpStatus = getAddressFromRoleResponse.statusCode();
		String getAddressFromRoleBody = getAddressFromRoleResponse.bodyToMono(String.class).block();
		if (!getAddressFromRoleHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(getAddressFromRoleHttpStatus, getAddressFromRoleBody);
		}
		MCListpermissions addressPermissionsList = gson.fromJson(getAddressFromRoleBody, MCListpermissions.class);
		if (addressPermissionsList.getPermission().size() == 0) {
			throw new WrapperException(HttpStatus.FORBIDDEN, "Address " + address + " has no valid role");
		} else if (addressPermissionsList.getPermission().size() > 1) {
			throw new WrapperException(HttpStatus.CONFLICT, "Address " + address + " has more than one role");
		}
		return addressPermissionsList.getPermission().get(0).getMctype();
	}
	
	/**
	 * This method sign a raw transaction using a provided private key. Uses Bitcoinj library to access transaction components
	 * @param signtx
	 * @param pvkey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public String signTransactionWithPrivKey(String signtx, String pvkey) throws NoSuchAlgorithmException, NoSuchProviderException {
		NetworkParameters np = MainNetParams.get();
		
		// Recreating keys from private key
		ECKey pkey = DumpedPrivateKey.fromBase58(np, pvkey).getKey();		
		
		// Decode transaction to sign
		RestRequestBody decoderawtransactionBody = new RestRequestBody();
		decoderawtransactionBody.setMethod("decoderawtransaction");
		List<Object> decoderawtransactionParams = new LinkedList<Object>();
		decoderawtransactionParams.add(signtx);
		decoderawtransactionBody.setParams(decoderawtransactionParams);
		ClientResponse decoderawtransactionResponse = webClient.post().bodyValue(decoderawtransactionBody).exchange().block();
		HttpStatus decoderawtransactionHttpStatus = decoderawtransactionResponse.statusCode();
		String decodetransactionResponseBody = decoderawtransactionResponse.bodyToMono(String.class).block();
		if (!decoderawtransactionHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(decoderawtransactionHttpStatus, decodetransactionResponseBody);
		}
		JsonObject decodedRawTransaction = JsonParser.parseString(decodetransactionResponseBody).getAsJsonObject().get("result").getAsJsonObject();
		
		// Get Transaction IDs for inputs and corresponding output index
		Map<String, Integer> vinsTxIDsAndVoutIDX = Utils.extractVINsTxIDsAndVoutIDX(decodedRawTransaction.toString());
		
		Transaction finalTx = new Transaction(np, Utils.hexStringToByteArray(signtx));
		
		Set<String> prevTxIDs = vinsTxIDsAndVoutIDX.keySet();
		int vinIDX = 0;
		for (String prevTxIDPlus : prevTxIDs) {
			String prevTxID = prevTxIDPlus.split("\\|")[0];
			Transaction tmpTx = new Transaction(np, Utils.hexStringToByteArray(signtx));
			
			// Retireve previous transaction
			RestRequestBody getrawtransactionBody = new RestRequestBody();
			getrawtransactionBody.setMethod("getrawtransaction");
			List<Object> getrawtransactionParams = new LinkedList<Object>();
			getrawtransactionParams.add(prevTxID);
			getrawtransactionBody.setParams(getrawtransactionParams);
			ClientResponse getrawtransactionResponse = webClient.post().bodyValue(getrawtransactionBody).exchange().block();
			HttpStatus getrawtransactionHttpStatus = getrawtransactionResponse.statusCode();
			String getrawtransactionResponseBody = getrawtransactionResponse.bodyToMono(String.class).block();
			if (!getrawtransactionHttpStatus.equals(HttpStatus.OK)) {
				throw new WrapperException(getrawtransactionHttpStatus, getrawtransactionResponseBody);
			}
			String prevRawTransaction = JsonParser.parseString(getrawtransactionResponseBody).getAsJsonObject().get("result").getAsString();
			
			// Extract "scriptPubKey" from vout and set as temporary "scriptSig" into corresponding vin
			Transaction prevTx = new Transaction(np, Utils.hexStringToByteArray(prevRawTransaction));
			Script rscriptPubKey = prevTx.getOutput(vinsTxIDsAndVoutIDX.get(prevTxIDPlus)).getScriptPubKey();
			tmpTx.getInput(vinIDX).setScriptSig(rscriptPubKey);
			
			/*
			 * A explained at: https://bitcoin.stackexchange.com/questions/3374/how-to-redeem-a-basic-tx
			 * 13. And at last, we write a four-byte "hash code type" (1 in our case): 01000000
			 * This is number of outputs
			 */
			String newTX = Utils.bytesToHex(tmpTx.bitcoinSerialize()) + "01000000";
			
			// Double Sha256 hashing of newTX 
			Sha256Hash doubleHashTX = Sha256Hash.twiceOf(Utils.hexStringToByteArray(newTX));
			
			// Public key only for input script
			ECKey pubkey = ECKey.fromPublicOnly(pkey.getPubKeyPoint());
			
			// Generating DER signature
			ECDSASignature signature = pkey.sign(doubleHashTX);
			
			// Creazione nuovo Input Script 
			TransactionSignature txs = new TransactionSignature(signature.r, signature.s);
			Script sigInput = ScriptBuilder.createInputScript(txs, pubkey);
			
			// Insert final "scriptSig" into final transaction
			finalTx.getInput(vinIDX).setScriptSig(sigInput);
			
			vinIDX++;
		}
		
		return Utils.bytesToHex(finalTx.bitcoinSerialize());
	}
	
	/**
	 * Retrieve a transaction from blockchain in raw format (decoded: false) or json format (decoded: true).
	 * Return a {@link JsonObject} of full MultiChain Response
	 * @param txid
	 * @param decoded
	 * @return
	 */
	public JsonObject getTransaction(String txid, boolean decoded) {
		JsonObject txReturn = null;
		RestRequestBody getrawtransactionBody = new RestRequestBody();
		getrawtransactionBody.setMethod("getrawtransaction");
		List<Object> getrawtransactionParams = new LinkedList<Object>();
		getrawtransactionParams.add(txid);
		getrawtransactionBody.setParams(getrawtransactionParams);
		ClientResponse getrawtransactionResponse = webClient.post().bodyValue(getrawtransactionBody).exchange().block();
		HttpStatus getrawtransactionHttpStatus = getrawtransactionResponse.statusCode();
		String getrawtransactionResponseBody = getrawtransactionResponse.bodyToMono(String.class).block();
		if (!getrawtransactionHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(getrawtransactionHttpStatus, getrawtransactionResponseBody);
		}
		txReturn = JsonParser.parseString(getrawtransactionResponseBody).getAsJsonObject();
		
		if (decoded) {
			RestRequestBody decoderawtransactionBody = new RestRequestBody();
			decoderawtransactionBody.setMethod("decoderawtransaction");
			List<Object> decoderawtransactionParams = new LinkedList<Object>();
			decoderawtransactionParams.add(txReturn.get("result").getAsString());
			decoderawtransactionBody.setParams(decoderawtransactionParams);
			ClientResponse decoderawtransactionResponse = webClient.post().bodyValue(decoderawtransactionBody).exchange().block();
			HttpStatus decoderawtransactionHttpStatus = decoderawtransactionResponse.statusCode();
			String decoderawtransactionResponseBody = decoderawtransactionResponse.bodyToMono(String.class).block();
			if (!decoderawtransactionHttpStatus.equals(HttpStatus.OK)) {
				throw new WrapperException(decoderawtransactionHttpStatus, decoderawtransactionResponseBody);
			}
			txReturn = JsonParser.parseString(decoderawtransactionResponseBody).getAsJsonObject();
		}
		return txReturn;
	}
	
	/**
	 * Get burn address from "getinfo" JSON-RPC API
	 * @return
	 */
	public String getBurnAddress() {
		if (burnAddress == null) {
			this.burnAddress = retrieveAttributeFromGetInfo("burnaddress").getAsString();
		}
		return burnAddress;
	}

	/**
	 * Get all vouchers balances of an address  
	 * @param address
	 * @return
	 */
	public List<AssetBalance> getAddressBalances(String address) {
		// Call "getaddressbalances" and retrieve data
		RestRequestBody getaddressbalancesRequest = new RestRequestBody();
		getaddressbalancesRequest.setMethod("getaddressbalances");
		List<Object> getaddressbalancesParams = new LinkedList<Object>();
		getaddressbalancesParams.add(address);
		getaddressbalancesRequest.setParams(getaddressbalancesParams);
		
		ClientResponse getaddressbalancesResponse = webClient.post().bodyValue(getaddressbalancesRequest).exchange().block();
		HttpStatus getaddressbalancesHttpStatus = getaddressbalancesResponse.statusCode();
		String getaddressbalancesResponseBody = getaddressbalancesResponse.bodyToMono(String.class).block();
		if (!getaddressbalancesHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(getaddressbalancesHttpStatus, getaddressbalancesResponseBody);
		}
		
		List<AssetBalance> assetBalanceList = new LinkedList<AssetBalance>();
		JsonArray balances = JsonParser.parseString(getaddressbalancesResponseBody).getAsJsonObject().get("result").getAsJsonArray();
		Iterator<JsonElement> assetBalances = balances.iterator();
		while (assetBalances.hasNext()) {
			JsonObject assetBalance = assetBalances.next().getAsJsonObject();
			AssetBalance singleBalance = new AssetBalance(assetBalance.get("name").getAsString(), assetBalance.get("qty").getAsLong());
			assetBalanceList.add(singleBalance);
		}
		
		return assetBalanceList;
	}
	
	/**
	 * Get all assets related transactions of a given address
	 * @param address - Address to check
	 * @param assets - Get only transaction with this assets; if null or empty retrieves all
	 * @param incoming - If "true" get all incoming transaction (assets sent to address), if "false" get all outgoing transaction (assets received by address)
	 * @return
	 */
	public BalancesGetTransactions getAddessTransaction(String address, String[] assets, boolean incoming) {
		
		BalancesGetTransactions returnData = new BalancesGetTransactions();
		returnData.setType((incoming ? "incoming" : "outgoing"));
		returnData.setTransactions(new HashSet<BalanceTransaction>());
		
		// Call "listaddresstransactions" API
		RestRequestBody listaddresstransactionsRequest = new RestRequestBody();
		listaddresstransactionsRequest.setMethod("listaddresstransactions");
		List<Object> listaddresstransactionsParams = new LinkedList<Object>();
		listaddresstransactionsParams.add(address);
		// "listaddresstransactions" returns a default of 10 results, so put high number to get all...
		listaddresstransactionsParams.add(999999999);
		listaddresstransactionsRequest.setParams(listaddresstransactionsParams);
		
		ClientResponse listaddresstransactionsResponse = webClient.post().bodyValue(listaddresstransactionsRequest).exchange().block();
		HttpStatus listaddresstransactionsHttpStatus = listaddresstransactionsResponse.statusCode();
		String listaddresstransactionsResponseBody = listaddresstransactionsResponse.bodyToMono(String.class).block();
		if (!listaddresstransactionsHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(listaddresstransactionsHttpStatus, listaddresstransactionsResponseBody);
		}
		
		// Sort before using Arrays.binarySearch for searching only assets passed as argument
		if (assets != null) { 
			Arrays.sort(assets);
		}
		
		JsonArray result = JsonParser.parseString(listaddresstransactionsResponseBody).getAsJsonObject().get("result").getAsJsonArray();
		
		Gson gson = new Gson();
		
		Iterator<JsonElement> resultsIterator = result.iterator();
		while (resultsIterator.hasNext()) {
			JsonElement singleTransaction = resultsIterator.next();
			
			MCGetTransactionResult transactionResult = gson.fromJson(singleTransaction, MCGetTransactionResult.class);
			
			List<AssetBalance> transactionAssets = transactionResult.getBalance().getAssets();
			if (transactionAssets != null && transactionAssets.size() > 0) {
				
				BalanceTransaction balanceTransaction = new BalanceTransaction();
				balanceTransaction.setTxid(transactionResult.getTxid());
				balanceTransaction.setAssets(new HashSet<AssetBalance>());
				
				// Remove positive or negative transactions if incoming or outgoing and add to list
				for (AssetBalance transactionAsset : transactionAssets) {
					int foundIdx = Integer.MIN_VALUE;
					if (assets != null && assets.length > 0) {
						foundIdx = Arrays.binarySearch(assets, transactionAsset.getName());
					}
					if (foundIdx >= 0 || foundIdx == Integer.MIN_VALUE) {
						long qty = transactionAsset.getQty();
						if (incoming && qty > 0) {
							balanceTransaction.setSender(transactionResult.getAddresses().get(0));
							balanceTransaction.setReceiver(transactionResult.getMyaddresses().get(0));
							balanceTransaction.getAssets().add(transactionAsset);
							returnData.getTransactions().add(balanceTransaction);
						} else if (!incoming && qty < 0) {
							balanceTransaction.setSender(transactionResult.getMyaddresses().get(0));
							balanceTransaction.setReceiver(transactionResult.getAddresses().get(0));
							transactionAsset.setQty(Math.abs(qty));
							balanceTransaction.getAssets().add(transactionAsset);
							returnData.getTransactions().add(balanceTransaction);
						}
					}
				}
				
			}
		}
		
		return returnData;
	}
	
	/**
	 * Retrieves asset info
	 * @param assetName
	 * @return
	 */
	public String getAssetInfo(String assetName) {
		RestRequestBody body = new RestRequestBody();
		body.setMethod("getassetinfo");
		List<Object> params = new LinkedList<Object>();
		params.add(assetName);
		params.add(false);
		body.setParams(params);
		
		ClientResponse response = webClient.post().bodyValue(body).exchange().block();
		HttpStatus httpStatus = response.statusCode();
		String bodyReturn = response.bodyToMono(String.class).block();
		if (!httpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(httpStatus, bodyReturn);
		}
		return bodyReturn;
	}
	
	/**
	 * Checks is asset is expired
	 * @param assetName
	 * @return true if asset is expired, false otherwise
	 */
	public boolean isAssetExpired(String assetName) {
		String assetInfo = getAssetInfo(assetName);
		String expireString = JsonParser.parseString(assetInfo).getAsJsonObject().get("result").getAsJsonObject().get("details").getAsJsonObject().get("expire").getAsString();
		LocalDate expireDate = LocalDate.parse(expireString);
		return expireDate.isBefore(LocalDate.now());
	}
}
