package com.vantea.hoperay.multichainwrapper.services;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vantea.hoperay.multichainwrapper.exceptions.WrapperException;
import com.vantea.hoperay.multichainwrapper.multichain.MultichainServer;
import com.vantea.hoperay.multichainwrapper.multichain.RestRequestBody;
import com.vantea.hoperay.multichainwrapper.services.beans.AssetNameJSON;
import com.vantea.hoperay.multichainwrapper.services.beans.AssetParamsJSON;
import com.vantea.hoperay.multichainwrapper.services.beans.input.IssueMoreBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.IssueNewVoucherBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.Roles;
import com.vantea.hoperay.multichainwrapper.utils.MultichainUtils;

@RestController
@RequestMapping(value = "/issue")
public class Issue {

	private static Logger log = LogManager.getLogger(Issue.class);

	@Autowired
	private WebClient webClient;

	@Autowired
	private MultichainServer multichainServer;
	
	@Autowired
	private MultichainUtils multichainUtils;

	/**
	 * Issues a new voucher
	 * 
	 * @param body:
	 * {
	 * 	"name":"voucher_name",
	 *  "value":100,
	 *  "expiration":"2020-12-31",
	 *  "address_to":"12345678aBcDeFgHiJkLmNoPqRsTuVwXyz",
	 *  "quantity":9999999
	 * }
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/issue_new_voucher", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String issueNewVoucher(@Valid @RequestBody IssueNewVoucherBody body) throws JsonProcessingException {
		log.trace("----- Issue.issueNewVoucher -----");
		log.trace("BODY: " + body.toString());

		//Check if address has COMPANY Role (low3 on Multichain). Only COMPANY can receive new vouchers
		String receiverRole = multichainUtils.getRoleForAddress(body.getAddress_to());
		log.debug("Receiver " + body.getAddress_to() + " has " + Roles.resolve(receiverRole));
		if (!Roles.resolve(receiverRole).equals(Roles.COMPANY)) {
			throw new WrapperException(HttpStatus.FORBIDDEN, "Address " + body.getAddress_to() + " with role " + Roles.resolve(receiverRole) + " cannot receive new vouchers.");
		}
		
		Gson gson = new Gson();

		// Build asset name object
		JsonObject asset = new JsonObject();
		asset.addProperty("name", body.getName());
		asset.addProperty("open", Boolean.TRUE);
		log.debug("Asset: " + asset.toString());
		AssetNameJSON assetName = gson.fromJson(asset.toString(), AssetNameJSON.class);

		JsonObject customFields = new JsonObject();
		customFields.addProperty("expire", body.getExpiration().toString());
		customFields.addProperty("value", body.getValue());
		log.debug("Custom Fields: " + customFields.toString());
		AssetParamsJSON assetCustomFields = gson.fromJson(customFields.toString(), AssetParamsJSON.class);

		// Building request body
		RestRequestBody issueReqBody = new RestRequestBody();
		issueReqBody.setMethod("issuefrom");
		List<Object> params = new LinkedList<Object>();
		params.add(multichainServer.getAdminAddress()); // Issuer
		params.add(body.getAddress_to()); // Destination address
		params.add(assetName); // Asset name and if reissue permitted
		params.add(body.getQuantity()); // Quantity of new asset
		params.add(1); // Fraction
		params.add(0); // native-amount output (not used)
		params.add(assetCustomFields); // Custom fields JSON
		issueReqBody.setParams(params);
		log.trace("issueReqBody: " + issueReqBody.toString());

		// Send request and get response
		ClientResponse issuefromResponse = webClient.post().bodyValue(issueReqBody).exchange().block();
		HttpStatus issuefromHttpStatus = issuefromResponse.statusCode();
		log.debug("HTTP Status: " + issuefromHttpStatus.value());
		String issueResponseBody = issuefromResponse.bodyToMono(String.class).block();
		log.debug("BODY: " + issueResponseBody);
		if (!issuefromHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(issuefromHttpStatus, issueResponseBody);
		}
		
		// Subscription to the newly created asset
		RestRequestBody subscribeBody = new RestRequestBody();
		subscribeBody.setMethod("subscribe");
		List<Object> subscribeParams = new LinkedList<Object>();
		subscribeParams.add(assetName.getName());
		subscribeParams.add(true);
		subscribeBody.setParams(subscribeParams);
		log.trace("subscribeBody: " + subscribeBody.toString());
		ClientResponse subscribeResponse = webClient.post().bodyValue(subscribeBody).exchange().block();
		HttpStatus subscribeHttpStatus = subscribeResponse.statusCode();
		log.debug("HTTP Status: " + subscribeHttpStatus.value());
		if (!subscribeHttpStatus.equals(HttpStatus.OK)) {
			String subscribeResponseBody = subscribeResponse.bodyToMono(String.class).block();
			throw new WrapperException(subscribeHttpStatus, subscribeResponseBody);
		}

		return issueResponseBody;
	}

	/**
	 * Issue more of a given voucher
	 * @param body:
	 * {
     *  "address_to":"12345678aBcDeFgHiJkLmNoPqRsTuVwXyz",
     * 	"voucher_name":"name",
     * 	"quantity":123
     * }
	 * @return
	 */
	@RequestMapping(
			path = "/issue_more_voucher",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String issueMoreVoucher(@Valid @RequestBody IssueMoreBody body) {
		log.trace("----- Issue.issueMoreVoucher -----");
		
		//Check if address has COMPANY Role (low3 on Multichain). Only COMPANY can receive new vouchers
		String receiverRole = multichainUtils.getRoleForAddress(body.getAddress_to());
		log.debug("Receiver " + body.getAddress_to() + " has " + Roles.resolve(receiverRole));
		if (!Roles.resolve(receiverRole).equals(Roles.COMPANY)) {
			throw new WrapperException(HttpStatus.FORBIDDEN, "Address " + body.getAddress_to() + " with role " + Roles.resolve(receiverRole) + " cannot receive new vouchers.");
		}
				
		
		// Building request body
		RestRequestBody issueMoreReqBody = new RestRequestBody();
		issueMoreReqBody.setMethod("issuemorefrom");
		List<Object> params = new LinkedList<Object>();
		params.add(multichainServer.getAdminAddress()); // Issuer
		params.add(body.getAddress_to()); // Destination address
		params.add(body.getVoucher_name()); // Asset name
		params.add(body.getQuantity()); // Quantity of asset
		issueMoreReqBody.setParams(params);
		log.trace("issueMoreReqBody: " + issueMoreReqBody.toString());

		// Send request and get response
		ClientResponse issuefromResponse = webClient.post().bodyValue(issueMoreReqBody).exchange().block();
		HttpStatus issuefromHttpStatus = issuefromResponse.statusCode();
		log.debug("HTTP Status: " + issuefromHttpStatus.value());
		String issueResponseBody = issuefromResponse.bodyToMono(String.class).block();
		log.debug("BODY: " + issueResponseBody);
		if (!issuefromHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(issuefromHttpStatus, issueResponseBody);
		}

		return issueResponseBody;
	}

}
