/**
 * REST Services for Multichain Status
 */
package com.vantea.hoperay.multichainwrapper.services;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.vantea.hoperay.multichainwrapper.exceptions.WrapperException;
import com.vantea.hoperay.multichainwrapper.multichain.MultichainServer;
import com.vantea.hoperay.multichainwrapper.multichain.RestRequestBody;
import com.vantea.hoperay.multichainwrapper.services.beans.output.IsAlive;
import com.vantea.hoperay.multichainwrapper.services.beans.output.WrapperReturn;
import com.vantea.hoperay.multichainwrapper.utils.MultichainUtils;

@RestController
@RequestMapping(path = "/status")
@Validated
public class Status {
	
	private static Logger log = LogManager.getLogger(Status.class);
	
	@Autowired
	private MultichainServer multichainServer;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private MultichainUtils multichainUtils;
	
	/**
	 * Returns information about an asset set as parameter
	 * @param assetName
	 * @return
	 */
	@RequestMapping(
			path = "/get_asset_info/{assetname}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String getassetinfo(@PathVariable(name = "assetname", required = true) String assetName) {
		log.trace("----- Status.getassetinfo -----");
		log.debug("Details for asset " + assetName);
		
		//TODO: Eliminare
		log.warn(assetName + " isExpired: " + multichainUtils.isAssetExpired(assetName));
		
		String bodyReturn = multichainUtils.getAssetInfo(assetName);
		log.debug("BODY: " + bodyReturn);
		
		return bodyReturn;
	}
	
	/**
	 * Returns informations about blockchain 
	 * @return
	 */
	@RequestMapping(
			path = "/get_blockchain_info",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String getblockchaininfo() {
		log.trace("----- Status.getblockchaininfo -----");
		
		RestRequestBody body = new RestRequestBody();
		body.setMethod("getblockchaininfo");
		List<Object> params = new LinkedList<Object>();
		body.setParams(params);
		
		ClientResponse response = webClient.post().bodyValue(body).exchange().block();
		HttpStatus httpStatus = response.statusCode();
		log.debug("HTTP Status: " + httpStatus.value());
		String bodyReturn = response.bodyToMono(String.class).block();
		log.debug("BODY: " + bodyReturn);
		
		if (!httpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(httpStatus, bodyReturn);
		}
		
		return bodyReturn;
	}
	
	/**
	 * Returns information about transactions that are not yet confirmed on blockchain
	 * @return
	 */
	@RequestMapping(
			path = "/get_mempool_info",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String getmempoolinfo() {
		log.trace("----- Status.getmempoolinfo -----");
		
		RestRequestBody body = new RestRequestBody();
		body.setMethod("getmempoolinfo");
		List<Object> params = new LinkedList<Object>();
		body.setParams(params);
		
		ClientResponse response = webClient.post().bodyValue(body).exchange().block();
		HttpStatus httpStatus = response.statusCode();
		log.debug("HTTP Status: " + httpStatus.value());
		String bodyReturn = response.bodyToMono(String.class).block();
		log.debug("BODY: " + bodyReturn);
		
		if (!httpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(httpStatus, bodyReturn);
		}
		
		return bodyReturn;
	}

	/**
	 * Returns information about network
	 * @return
	 */
	@RequestMapping(
			path = "/get_network_info",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String getnetworkinfo() {
		log.trace("----- Status.getnetworkinfo -----");
		
		RestRequestBody body = new RestRequestBody();
		body.setMethod("getnetworkinfo");
		List<Object> params = new LinkedList<Object>();
		body.setParams(params);
		
		ClientResponse response = webClient.post().bodyValue(body).exchange().block();
		HttpStatus httpStatus = response.statusCode();
		log.debug("HTTP Status: " + httpStatus.value());
		String bodyReturn = response.bodyToMono(String.class).block();
		log.debug("BODY: " + bodyReturn);
		
		if (!httpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(httpStatus, bodyReturn);
		}
		
		return bodyReturn;
	}
	
	@RequestMapping(
			path = "/get_peer_info/{ping}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String getpeerinfo(@PathVariable(name = "ping", required = true) boolean ping) {
		log.trace("----- Status.getpeerinfo -----");
		log.debug("Get peer info with ping " + ping);
		
		// If ping is true execute ping command and waits for a given time (configured in multichain.rpc.nodePingTimeoutMs) before "getpeerinfo"
		if (ping) {
			RestRequestBody body = new RestRequestBody();
			body.setMethod("ping");
			List<Object> params = new LinkedList<Object>();
			body.setParams(params);
			
			// Send and get response
			ClientResponse response = webClient.post().bodyValue(body).exchange().block();
			HttpStatus httpStatus = response.statusCode();
			log.debug("HTTP Status: " + httpStatus.value());
			String bodyReturn = response.bodyToMono(String.class).block();
			log.debug("BODY: " + bodyReturn);
			if (!httpStatus.equals(HttpStatus.OK)) {
				throw new WrapperException(httpStatus, bodyReturn);
			}
			
			log.trace("Awaits for " + multichainServer.getNodePingTimeoutMs() + " MS");
			try {
				Thread.sleep(multichainServer.getNodePingTimeoutMs());
			} catch (InterruptedException e) {
				throw new WrapperException(HttpStatus.INTERNAL_SERVER_ERROR, "Error on ping command");
			}
		}
		
		log.trace("Now executing \"getpeerinfo\"");
		RestRequestBody body = new RestRequestBody();
		body.setMethod("getpeerinfo");
		List<Object> params = new LinkedList<Object>();
		body.setParams(params);

		// Send and get response
		ClientResponse response = webClient.post().bodyValue(body).exchange().block();
		HttpStatus httpStatus = response.statusCode();
		log.debug("HTTP Status: " + httpStatus.value());
		String bodyReturn = response.bodyToMono(String.class).block();
		log.debug("BODY: " + bodyReturn);
		if (!httpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(httpStatus, bodyReturn);
		}
		
		return bodyReturn;
	}
	
	/**
	 * Check wrapper status
	 * @return
	 */
	@RequestMapping(
			path = "/isalive",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public WrapperReturn isAlive() {
		log.trace("----- Status.isAlive -----");
		IsAlive isAliveResult = new IsAlive(System.currentTimeMillis(), "OK");
		WrapperReturn wr = new WrapperReturn(isAliveResult, null, null);
		return wr;
	}
	
}
