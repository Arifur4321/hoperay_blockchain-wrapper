package com.vantea.hoperay.multichainwrapper.services;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.vantea.hoperay.multichainwrapper.dbconfig.INPlaceConnection;
import com.vantea.hoperay.multichainwrapper.exceptions.WrapperException;
import com.vantea.hoperay.multichainwrapper.multichain.MultichainServer;
import com.vantea.hoperay.multichainwrapper.multichain.RestRequestBody;
import com.vantea.hoperay.multichainwrapper.services.beans.AssetNameJSON;
import com.vantea.hoperay.multichainwrapper.services.beans.AssetParamJsonCambioStatoFasciolo;
import com.vantea.hoperay.multichainwrapper.services.beans.AssetParamsJSON;
import com.vantea.hoperay.multichainwrapper.services.beans.AssetParamsJsonCaricamento;
import com.vantea.hoperay.multichainwrapper.services.beans.AssetParamsJsonLettura;
import com.vantea.hoperay.multichainwrapper.services.beans.AssetParamsJsonMessage;
import com.vantea.hoperay.multichainwrapper.services.beans.InpPlaceTcliente;
import com.vantea.hoperay.multichainwrapper.services.beans.InpPlaceTfascicolo;
import com.vantea.hoperay.multichainwrapper.services.beans.input.Cambiostatofascicolobean;
import com.vantea.hoperay.multichainwrapper.services.beans.input.InplaceDocumentobean;
import com.vantea.hoperay.multichainwrapper.services.beans.input.InplaceLetturabean;
import com.vantea.hoperay.multichainwrapper.services.beans.input.InplaceMessagebean;
import com.vantea.hoperay.multichainwrapper.services.beans.input.IssueMoreBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.IssueNewVoucherBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.NewKeyPairBody;
import com.vantea.hoperay.multichainwrapper.services.beans.input.Roles;
import com.vantea.hoperay.multichainwrapper.services.beans.input.TransferVoucherFromBody;
import com.vantea.hoperay.multichainwrapper.services.beans.output.KeyPairAndAddress;
import com.vantea.hoperay.multichainwrapper.services.beans.output.TransferTX;
import com.vantea.hoperay.multichainwrapper.services.beans.output.WrapperReturn;
import com.vantea.hoperay.multichainwrapper.utils.MultichainUtils;
import com.vantea.hoperay.multichainwrapper.utils.Utils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


@PropertySource("classpath:/config.properties")
@RestController
@RequestMapping(path = "/Inplace_Lettura")
public class InplaceLettura {


	@Value("${inplace_password}")
	private String password;
	

	@Value("${inplace_userAuthorization}")
	private String userAuthorization;


	private static Logger log = LogManager.getLogger(InpPlaceDataUpdate.class);
	
	//public  String userAuthorization = "Inplace" ;
	//public  String password ="Inplace987654321";
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private MultichainUtils multichainUtils;
	
	@Autowired
	private MultichainServer multichainServer;
	
	@RequestMapping(value = "/Inplace_ConfermaLetturaMessaggio", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String issueNewVoucher(@Valid @RequestBody InplaceLetturabean body) throws JsonProcessingException {
		log.trace("----- Issue.issueNewVoucher -----");
		log.trace("BODY: " + body.toString());
   
		
		log.debug("userAuthorization" + userAuthorization);
		
//	if (  body.getUserAuthorization().equals(userAuthorization) && body.getPassword().equals(password) ) {
           
		log.debug("authorization okey");
		
		//Check if address has COMPANY Role (low3 on Multichain). Only COMPANY can receive new vouchers
		//String receiverRole = multichainUtils.getRoleForAddress(body.getAddress_to());
		//log.debug("Receiver " + body.getAddress_to() + " has " + Roles.resolve(receiverRole));
		//if (!Roles.resolve(receiverRole).equals(Roles.COMPANY)) {
	    //	throw new WrapperException(HttpStatus.FORBIDDEN, "Address " + body.getAddress_to() + " with role " + Roles.resolve(receiverRole) + " cannot receive new vouchers.");
		//  }
		
		Gson gson = new Gson();
	     DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
         Date date = new Date();
         String time=dateFormat.format(date);
		// Build asset name object
		JsonObject asset = new JsonObject();
		asset.addProperty("name", body.getUserName()+"-"+time);
		asset.addProperty("open", Boolean.TRUE);
		log.debug("Asset: " + asset.toString());
		AssetNameJSON assetName = gson.fromJson(asset.toString(), AssetNameJSON.class);

		JsonObject customFields = new JsonObject();
		//customFields.addProperty("expire", body.getExpiration().toString());
//		customFields.addProperty("value", body.getValue());
		customFields.addProperty("userName", body.getUserName()+"--"+time);
		customFields.addProperty("idFascicolo", body.getIdFascicolo());
		customFields.addProperty("idMessaggioAlert", body.getIdMessaggioAlert());
		 
		if (body.isStatoLettura() == true ) {
			customFields.addProperty("statoLettura", "cancellato");
			
		}else {
			
			customFields.addProperty("statoLettura", "UNDO");
		}
		
		customFields.addProperty("descrizione", body.getDescrizione());
		customFields.addProperty("tipo_oprazione", "Conferma Lettura Messagio nel Fascicolo");
		
		log.debug("Custom Fields: " + customFields.toString());
		AssetParamsJsonLettura assetCustomFields = gson.fromJson(customFields.toString(), AssetParamsJsonLettura.class);

		// Building request body
		RestRequestBody issueReqBody = new RestRequestBody();
		issueReqBody.setMethod("issuefrom");
		List<Object> params = new LinkedList<Object>();
		params.add(multichainServer.getAdminAddress()); // Issuer
		params.add(body.getAddress_to()); // Destination address
		params.add(assetName); // Asset name and if reissue permitted
		params.add(1); // Quantity of new asset
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



	@RequestMapping(value = "/Inplace_spegnimentoAlert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String issueNewVoucherLettura(@Valid @RequestBody InplaceLetturabean body) throws JsonProcessingException {
		log.trace("----- Issue.issueNewVoucher -----");
		log.trace("BODY: " + body.toString());

		
	//if (  body.getUserAuthorization().equals(userAuthorization) && body.getPassword().equals(password) ) {
           
		log.debug("authorization okey");
		
		//Check if address has COMPANY Role (low3 on Multichain). Only COMPANY can receive new vouchers
		//String receiverRole = multichainUtils.getRoleForAddress(body.getAddress_to());
		//log.debug("Receiver " + body.getAddress_to() + " has " + Roles.resolve(receiverRole));
		//if (!Roles.resolve(receiverRole).equals(Roles.COMPANY)) {
	    //	throw new WrapperException(HttpStatus.FORBIDDEN, "Address " + body.getAddress_to() + " with role " + Roles.resolve(receiverRole) + " cannot receive new vouchers.");
		//  }
		
		Gson gson = new Gson();
	     DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
         Date date = new Date();
         String time=dateFormat.format(date);
		// Build asset name object
		JsonObject asset = new JsonObject();
		asset.addProperty("name", body.getUserName()+"-"+time);
		asset.addProperty("open", Boolean.TRUE);
		log.debug("Asset: " + asset.toString());
		AssetNameJSON assetName = gson.fromJson(asset.toString(), AssetNameJSON.class);

		JsonObject customFields = new JsonObject();
		//customFields.addProperty("expire", body.getExpiration().toString());
//		customFields.addProperty("value", body.getValue());
		customFields.addProperty("userName", body.getUserName()+"--"+time);
		customFields.addProperty("idFascicolo", body.getIdFascicolo());
		customFields.addProperty("idMessaggioAlert", body.getIdMessaggioAlert());
		if (body.isStatoLettura() == true ) {
			customFields.addProperty("statoLettura", "cancellato");
			
		}else {
			
			customFields.addProperty("statoLettura", "UNDO");
		}
		customFields.addProperty("descrizione", body.getDescrizione());
		customFields.addProperty("tipo_oprazione", "Spegnimento Alert del Fascicolo");
		
		log.debug("Custom Fields: " + customFields.toString());
		AssetParamsJsonLettura assetCustomFields = gson.fromJson(customFields.toString(), AssetParamsJsonLettura.class);

		// Building request body
		RestRequestBody issueReqBody = new RestRequestBody();
		issueReqBody.setMethod("issuefrom");
		List<Object> params = new LinkedList<Object>();
		params.add(multichainServer.getAdminAddress()); // Issuer
		params.add(body.getAddress_to()); // Destination address
		params.add(assetName); // Asset name and if reissue permitted
		params.add(1); // Quantity of new asset
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



}
