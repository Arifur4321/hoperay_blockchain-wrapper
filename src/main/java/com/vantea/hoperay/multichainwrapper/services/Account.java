/**
 * REST Services for account management
 */
package com.vantea.hoperay.multichainwrapper.services;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.LinkedList;
import java.util.List;

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

import com.vantea.hoperay.multichainwrapper.exceptions.WrapperException;
import com.vantea.hoperay.multichainwrapper.multichain.MultichainServer;
import com.vantea.hoperay.multichainwrapper.multichain.RestRequestBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.NewKeyPairBody;
import com.vantea.hoperay.multichainwrapper.services.beans.output.KeyPairAndAddress;
import com.vantea.hoperay.multichainwrapper.services.beans.output.WrapperReturn;
import com.vantea.hoperay.multichainwrapper.utils.Utils;

@RestController
@RequestMapping(path = "/account")
public class Account {

	private static Logger log = LogManager.getLogger(Account.class);
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private MultichainServer multichainServer;
	
	//TODO: Verificare seed
	/**
	 * Generates private key public key and address, give permission to send and receive assets and registers it as "watch-only address" in admin node 
	 * @param body - {@link NewKeyPairBody}
	 * {
	 *  "seed":null|"something"
     * }
	 * @return Keys (private/public and address)
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchProviderException
	 */
	@RequestMapping(
			path = "/new_keypair",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public WrapperReturn newKeyPair(@RequestBody NewKeyPairBody restbody) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchProviderException {
		log.trace("----- Account.newKeyPair -----");
		String seed = null;
		if (restbody != null && restbody.getSeed()!= null) {
			log.debug("Generating using seed");
			seed = restbody.getSeed();
		}
		
		log.warn("SEED: " + seed);
		
		// Generate keys and address
		KeyPairAndAddress keys2 = Utils.generateKeyPair(seed);
		log.debug("Keys2: " + keys2);
		
		// Set Send-Receive Permissions for new address
		RestRequestBody body2 = new RestRequestBody();
		body2.setMethod("grantfrom");
		List<Object> params2 = new LinkedList<Object>();
		params2.add(multichainServer.getAdminAddress()); // Admin Address From
		params2.add(keys2.getAddress()); // New Address
		params2.add("send,receive"); // SEND and RECEIVE permissions
		body2.setParams(params2);
		ClientResponse response2 = webClient.post().bodyValue(body2).exchange().block();
		HttpStatus httpStatus2 = response2.statusCode();
		log.debug("HTTP Status: " + httpStatus2.value());
		String bodyReturn2 = response2.bodyToMono(String.class).block();
		log.debug("BODY: " + bodyReturn2);
		if (!httpStatus2.equals(HttpStatus.OK)) {
			throw new WrapperException(httpStatus2, bodyReturn2);
		}
		
		// Add address to "watch-only" list
		RestRequestBody body = new RestRequestBody();
		body.setMethod("importaddress");
		List<Object> params = new LinkedList<Object>();
		params.add(keys2.getAddress()); // Address
		params.add(keys2.getAddress()); // Label
		params.add(false); // Rescan
		body.setParams(params);
		ClientResponse response = webClient.post().bodyValue(body).exchange().block();
		HttpStatus httpStatus = response.statusCode();
		log.debug("HTTP Status: " + httpStatus.value());
		String bodyReturn = response.bodyToMono(String.class).block();
		log.debug("BODY: " + bodyReturn);
		if (!httpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(httpStatus, bodyReturn);
		}
		
		WrapperReturn wpReturn = new WrapperReturn(keys2, null, null);
		
		return wpReturn;
	}
	
}

