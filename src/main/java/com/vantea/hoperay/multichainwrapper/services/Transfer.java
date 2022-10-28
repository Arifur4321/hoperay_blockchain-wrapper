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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vantea.hoperay.multichainwrapper.exceptions.WrapperException;
import com.vantea.hoperay.multichainwrapper.multichain.RestRequestBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.BurnVouchersBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.Destination;
import com.vantea.hoperay.multichainwrapper.services.beans.input.Roles;
import com.vantea.hoperay.multichainwrapper.services.beans.input.TransferVoucherFromBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.TransferVoucherFromMany;
import com.vantea.hoperay.multichainwrapper.services.beans.output.TransferTX;
import com.vantea.hoperay.multichainwrapper.services.beans.output.WrapperReturn;
import com.vantea.hoperay.multichainwrapper.utils.MultichainUtils;

@RestController
@RequestMapping(path = "/transfer")
public class Transfer {

	private static Logger log = LogManager.getLogger(Transfer.class);
	
	@Autowired
	private MultichainUtils multichainUtils;

	@Autowired
	private WebClient webClient;

	/**
	 * Prepares a transfer request. Output must be signed with sender private key before send. 
	 * @param body - {@link TransferVoucherFromBody}:
	 * {
     *  "address_from": "12345678aBcDeFgHiJkLmNoPqRsTuVwXyz",
     *  "address_to": "12345678aBcDeFgHiJkLmNoPqRsTuVwXyz",
     *  "transfer": [
     *   {
     *    "name": "voucher_name",
     *    "qty": 1
     *   }
     *  ]
     * }
	 * @return Raw Transaction to sign
	 */
	@RequestMapping(
			path = "/transfer_voucher_from",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public WrapperReturn transferVoucherFrom(@Valid @RequestBody TransferVoucherFromBody body) {
		log.trace("----- Transfer.transferVoucherFrom -----");
		log.debug("BODY: " + body);

		Gson gson = new Gson();

		// Get role of sender address
		String senderRole = multichainUtils.getRoleForAddress(body.getAddress_from());
		log.debug("Sender " + body.getAddress_from() + " has " + Roles.resolve(senderRole));

		// Get Role of destination address
		String destinationRole = multichainUtils.getRoleForAddress(body.getAddress_to());
		log.debug("Receiver " + body.getAddress_to() + " has " + Roles.resolve(destinationRole));

		// Check if sender can send to receiver checking roles
		if (log.isTraceEnabled()) {
			log.trace("CHECK SEND: " + Roles.resolve(senderRole) + " -> " + Roles.resolve(destinationRole));
		}
		boolean canSend = multichainUtils.canSendByRole(Roles.resolve(senderRole), Roles.resolve(destinationRole));
		if (!canSend) {
			throw new WrapperException(HttpStatus.FORBIDDEN, body.getAddress_from() + "[" + Roles.resolve(senderRole) + "] cannot send to " + body.getAddress_to() + "[" + Roles.resolve(destinationRole) + "]");
		}

		// Create voucher data
		JsonObject sendData = multichainUtils.createParamForVoucherTransfer(body.getAddress_to(), body.getTransfer(), true);

		// Now call "createrawsendfrom" to get raw transaction to sign
		RestRequestBody rawsendRequest = new RestRequestBody();
		rawsendRequest.setMethod("createrawsendfrom");

		Object sendDataParam = gson.fromJson(sendData, Object.class);

		List<Object> rawsendParams = new LinkedList<Object>();
		rawsendParams.add(body.getAddress_from());
		rawsendParams.add(sendDataParam);
		rawsendRequest.setParams(rawsendParams);

		ClientResponse rawsendResponse = webClient.post().bodyValue(rawsendRequest).exchange().block();
		HttpStatus rawsendResponseHttpStatus = rawsendResponse.statusCode();
		log.debug("HTTP Status: " + rawsendResponseHttpStatus.value());
		String rawsendResponseBody = rawsendResponse.bodyToMono(String.class).block();
		log.debug("BODY: " + rawsendResponseBody);
		if (!rawsendResponseHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(rawsendResponseHttpStatus, rawsendResponseBody);
		}

		JsonObject fullresponse = JsonParser.parseString(rawsendResponseBody).getAsJsonObject();
		String result = fullresponse.get("result").getAsString();
		
		TransferTX transferTX = new TransferTX(result, false);

		return new WrapperReturn(transferTX, null, null);
	}

	/**
	 * Prepares a burn transaction. Output must be signed with sender private key before send.
	 * Only addresses with "MERCHANT" role ("low1" permission on Multichain) can burn vouchers.
	 * @param body - {@link BurnVouchersBody}
	 * {
     *  "address_from": "12345678aBcDeFgHiJkLmNoPqRsTuVwXyz",
     *  "address_to": "12345678aBcDeFgHiJkLmNoPqRsTuVwXyz",
     *  "transfer": [
     *   {
     *    "name": "voucher_name",
     *    "qty": 1
     *   }
     *  ]
     * }
	 * @return Raw transaction to sign
	 */
	@RequestMapping(
			path = "/burn_vouchers",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public WrapperReturn burnVouchers(@Valid @RequestBody BurnVouchersBody body) {
		log.trace("----- Transfer.burnVouchers -----");
		log.debug("BODY: " + body);

		Gson gson = new Gson();

		// Check if address has Merchant Role (low1 on Multichain)
		String senderRole = multichainUtils.getRoleForAddress(body.getAddress_from());
		log.debug("Sender " + body.getAddress_from() + " has " + Roles.resolve(senderRole));
		if (!Roles.resolve(senderRole).equals(Roles.MERCHANT)) {
			throw new WrapperException(HttpStatus.FORBIDDEN, "Address " + body.getAddress_from() + " with role " + Roles.resolve(senderRole) + " cannot burn vouchers.");
		}

		// Retrieve Burn Address from "getinfo"
		String burnAddress = multichainUtils.getBurnAddress();
		log.info("Burning voucher sending to " + burnAddress);

		// Create voucher data skipping expiration check because of "burn" transaction
		JsonObject sendData = multichainUtils.createParamForVoucherTransfer(burnAddress, body.getTransfer(), false);
		log.debug("Send data: " + sendData);

		// Now call "createrawsendfrom" setting "burn address" as destination to get raw transaction to sign
		RestRequestBody rawsendRequest = new RestRequestBody();
		rawsendRequest.setMethod("createrawsendfrom");

		Object sendDataParam = gson.fromJson(sendData, Object.class);

		List<Object> rawsendParams = new LinkedList<Object>();
		rawsendParams.add(body.getAddress_from());
		rawsendParams.add(sendDataParam);
		rawsendRequest.setParams(rawsendParams);

		ClientResponse rawsendResponse = webClient.post().bodyValue(rawsendRequest).exchange().block();
		HttpStatus rawsendResponseHttpStatus = rawsendResponse.statusCode();
		log.debug("HTTP Status: " + rawsendResponseHttpStatus.value());
		String rawsendResponseBody = rawsendResponse.bodyToMono(String.class).block();
		log.debug("BODY: " + rawsendResponseBody);
		if (!rawsendResponseHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(rawsendResponseHttpStatus, rawsendResponseBody);
		}
		
		JsonObject fullresponse = JsonParser.parseString(rawsendResponseBody).getAsJsonObject();
		String result = fullresponse.get("result").getAsString();
		
		TransferTX transferTX = new TransferTX(result, false);

		return new WrapperReturn(transferTX, null, null);
	}
	
	//TODO: transfer_voucher_from_many
	@RequestMapping(
			path = "/transfer_voucher_from_many",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public WrapperReturn transferVoucherFromMany(@Valid @RequestBody TransferVoucherFromMany body) {
		log.trace("----- Transfer.transferVoucherFromMany -----");
		log.debug("BODY: " + body);
		
		Gson gson = new Gson();
		
		// Get role of sender address
		String senderRole = multichainUtils.getRoleForAddress(body.getAddress_from());
		log.debug("Sender " + body.getAddress_from() + " has " + Roles.resolve(senderRole));

//		JsonArray receiverFullList = new JsonArray();
		JsonObject receiverFullList = new JsonObject();
		
		List<Destination> destinations = body.getDestinations();
		
		// For any destination address create jsonelement with voucher and quantity
		for (Destination destination : destinations) {
			
			// Get Role of destination address
			String destinationRole = multichainUtils.getRoleForAddress(destination.getAddress_to());
			log.debug("Receiver " + destination.getAddress_to() + " has " + Roles.resolve(destinationRole));

			// Check if sender can send to receiver checking roles
			if (log.isTraceEnabled()) {
				log.trace("CHECK SEND: " + Roles.resolve(senderRole) + " -> " + Roles.resolve(destinationRole));
			}
			boolean canSend = multichainUtils.canSendByRole(Roles.resolve(senderRole), Roles.resolve(destinationRole));
			if (!canSend) {
				throw new WrapperException(HttpStatus.FORBIDDEN, body.getAddress_from() + "[" + Roles.resolve(senderRole) + "] cannot send to " + destination.getAddress_to() + "[" + Roles.resolve(destinationRole) + "]");
			}
			
			// Create voucher data
			JsonObject sendData = multichainUtils.createParamForVoucherTransfer(destination.getAddress_to(), destination.getTransfer(), true);
			log.trace("sendData: " + sendData);
			JsonElement vouchers = sendData.get(destination.getAddress_to());
			log.debug("For [" + destination.getAddress_to() + "]: " + vouchers.toString());
//			receiverFullList.add(sendData);
			receiverFullList.add(destination.getAddress_to(), vouchers);
		}
		log.trace("receiverFullList: " + receiverFullList.toString());
		
		// Now call "createrawsendfrom" to get raw transaction to sign
		RestRequestBody rawsendRequest = new RestRequestBody();
		rawsendRequest.setMethod("createrawsendfrom");

		Object sendDataParam = gson.fromJson(receiverFullList, Object.class);

		List<Object> rawsendParams = new LinkedList<Object>();
		rawsendParams.add(body.getAddress_from());
		rawsendParams.add(sendDataParam);
		rawsendRequest.setParams(rawsendParams);
		
		log.trace("Multireceiver data: " + rawsendRequest.toString());
		
		ClientResponse rawsendResponse = webClient.post().bodyValue(rawsendRequest).exchange().block();
		HttpStatus rawsendResponseHttpStatus = rawsendResponse.statusCode();
		log.debug("HTTP Status: " + rawsendResponseHttpStatus.value());
		String rawsendResponseBody = rawsendResponse.bodyToMono(String.class).block();
		log.debug("BODY: " + rawsendResponseBody);
		if (!rawsendResponseHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(rawsendResponseHttpStatus, rawsendResponseBody);
		}
		
		JsonObject fullresponse = JsonParser.parseString(rawsendResponseBody).getAsJsonObject();
		String result = fullresponse.get("result").getAsString();
		
		TransferTX transferTX = new TransferTX(result, false);

		return new WrapperReturn(transferTX, null, null);
	}
	
}
