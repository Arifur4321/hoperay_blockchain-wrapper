/**
 * Main Spring Configuration
 */
package com.vantea.hoperay.multichainwrapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.reactive.function.client.WebClient;

import com.vantea.hoperay.multichainwrapper.multichain.MultichainServer;
import com.vantea.hoperay.multichainwrapper.utils.MultichainUtils;

@Configuration
public class MainConfiguration {

	@Value("${multichain.rpc.host}")
	private String multichainRpcHost;

	@Value("${multichain.rpc.auth}")
	private String multichainRpcAuth;

	@Value("${multichain.rpc.port}")
	private int multichainRpcPort;

	@Value("${multichain.rpc.baseurl}")
	private String multichainRpcBaseurl;

	@Value("${multichain.rpc.adminAddress}")
	private String multichainAdminAddress;
	
	@Value("${multichain.rpc.nodePingTimeoutMs}") 
	private long multichainNodePingTimeoutMs;

	@Bean
	@Order(value = 0)
	public MultichainServer multichainServer() {
		MultichainServer multichainServer = new MultichainServer();
		multichainServer.setHost(multichainRpcHost);
		multichainServer.setBase64Auth(multichainRpcAuth);
		multichainServer.setPort(multichainRpcPort);
		multichainServer.setAdminAddress(multichainAdminAddress);
		multichainServer.setNodePingTimeoutMs(multichainNodePingTimeoutMs);
		return multichainServer;
	}

	@Bean
	@Order(value = 1)
	public WebClient webClient() {
		return WebClient.builder().baseUrl(multichainRpcBaseurl)
				.defaultHeader("Authorization", "Basic " + multichainRpcAuth)
				.defaultHeader("Content-Type", "application/json").build();
	}
	
	@Bean
	@Order(value = 2)
	public MultichainUtils multichainUtils() {
		return new MultichainUtils();
	}
	
	
	
}
