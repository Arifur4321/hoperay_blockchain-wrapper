/**
 * Rest Services for permissions management
 */
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
import com.vantea.hoperay.multichainwrapper.exceptions.WrapperException;
import com.vantea.hoperay.multichainwrapper.multichain.MultichainServer;
import com.vantea.hoperay.multichainwrapper.multichain.RestRequestBody;
import com.vantea.hoperay.multichainwrapper.services.beans.MCListpermissions;
import com.vantea.hoperay.multichainwrapper.services.beans.MCListpermissions.Permission;
import com.vantea.hoperay.multichainwrapper.services.beans.input.GrantAccountPermissionsBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.RevokeAccountPermissionsBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.Roles;

@RestController
@RequestMapping(path = "/permissions")
public class Permissions {

	private static Logger log = LogManager.getLogger(Permissions.class);
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private MultichainServer multichainServer;
	
	/**
	 * Grant role to address.
	 * Before granting role all permissions are deleted to have only one permission in addition to "send" and "receive"
	 * @param body - JSON Containing address and role:
	 * {
	 *  "address":"12345678aBcDeFgHiJkLmNoPqRsTuVwXyz",
	 *  "role":"COMPANY|EMPLOYEE|MERCHANT"
	 * }
	 * @return
	 */
	@RequestMapping(
			path = "/grant_account_permissions",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String grantAccountPermissions(@Valid @RequestBody GrantAccountPermissionsBody body) {
		log.trace("----- Permissions.grantAccountPermissions -----");
		log.trace("BODY:" + body);
		
		/* STEP 1: Revoke all permissions */
		// Revoke all permissions
		RestRequestBody mcBody = new RestRequestBody();
		mcBody.setMethod("revoke");
		List<Object> params = new LinkedList<Object>();
		params.add(body.getAddress()); // New Address
		params.add("*");
		mcBody.setParams(params);
		ClientResponse listPermissionsResponse = webClient.post().bodyValue(mcBody).exchange().block();
		HttpStatus listpermshttpStatus = listPermissionsResponse.statusCode();
		log.debug("HTTP Status: " + listpermshttpStatus.value());
		String addresspermissions = listPermissionsResponse.bodyToMono(String.class).block();
		log.debug("BODY: " + addresspermissions);
		if (!listpermshttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(listpermshttpStatus, addresspermissions);
		}
		
		/* STEP 2: Set permissions sent as body and adds "send" and "receive" permissions */
		// Set permissions address
		RestRequestBody body2 = new RestRequestBody();
		body2.setMethod("grantfrom");
		List<Object> params2 = new LinkedList<Object>();
		params2.add(multichainServer.getAdminAddress()); // Admin Address From
		params2.add(body.getAddress()); // New Address
		params2.add("send,receive," + Roles.toMcRole(body.getRole().getRole())); // Set role and adds SEND and RECEIVE permissions
		log.trace("Grant Request Params: " + params2);
		body2.setParams(params2);
		ClientResponse response2 = webClient.post().bodyValue(body2).exchange().block();
		HttpStatus httpStatus2 = response2.statusCode();
		log.debug("HTTP Status: " + httpStatus2.value());
		String bodyReturn2 = response2.bodyToMono(String.class).block();
		log.debug("BODY: " + bodyReturn2);
		if (!httpStatus2.equals(HttpStatus.OK)) {
			throw new WrapperException(httpStatus2, bodyReturn2);
		}
		return bodyReturn2;
	}
	
	@RequestMapping(
			path = "/revoke_account_permissions",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	/**
	 * Revokes all permissions of a given address
	 * @param body - JSON containing address:
	 * {
	 *  "address":"12345678aBcDeFgHiJkLmNoPqRsTuVwXyz"
	 * }
	 * @return
	 */
	public String revokeAccountPermissions(@RequestBody RevokeAccountPermissionsBody body) {
		log.trace("----- Permissions.revokeAccountPermissions -----");
		log.trace("BODY:" + body);
		
		// Retrieve all permissions
		RestRequestBody mcBody = new RestRequestBody();
		mcBody.setMethod("listpermissions");
		List<Object> params = new LinkedList<Object>();
		params.add("*");
		params.add(body.getAddress()); // New Address
		mcBody.setParams(params);
		ClientResponse listPermissionsResponse = webClient.post().bodyValue(mcBody).exchange().block();
		HttpStatus listpermshttpStatus = listPermissionsResponse.statusCode();
		log.debug("HTTP Status: " + listpermshttpStatus.value());
		String addresspermissions = listPermissionsResponse.bodyToMono(String.class).block();
		log.debug("BODY: " + addresspermissions);
		if (!listpermshttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(listpermshttpStatus, addresspermissions);
		}
		
		// Building permissions list for address
		Gson permissionsJson = new Gson();
		MCListpermissions addressPermissionsList = permissionsJson.fromJson(addresspermissions, MCListpermissions.class);
		log.trace("Permissions size: " + addressPermissionsList.getPermission().size());
		StringBuffer sb = new StringBuffer();
		List<Permission> permissionsObj = addressPermissionsList.getPermission();
		for (Permission permission : permissionsObj) {
			sb.append(",");
			sb.append(permission.getMctype());
		}
		sb.deleteCharAt(0);
		log.debug("Revoke " + sb.toString()  + " to " + body.getAddress());
		
		// Call "revokefrom" using JSON-RPC 
		RestRequestBody revokeBody = new RestRequestBody();
		revokeBody.setMethod("revokefrom");
		List<Object> revokeParams = new LinkedList<Object>();
		revokeParams.add(multichainServer.getAdminAddress());
		revokeParams.add(body.getAddress());
		revokeParams.add(sb.toString());
		revokeBody.setParams(revokeParams);
		ClientResponse revokeResponse = webClient.post().bodyValue(revokeBody).exchange().block();
		HttpStatus revokeHttpStatus = revokeResponse.statusCode();
		log.debug("HTTP revoke Status: " + revokeHttpStatus.value());
		String revokeResult = revokeResponse.bodyToMono(String.class).block();
		log.debug("revoke BODY: " + revokeResult);
		if (!revokeHttpStatus.equals(HttpStatus.OK)) {
			throw new WrapperException(revokeHttpStatus, revokeResult);
		}
		
		return revokeResult;
	}
}
