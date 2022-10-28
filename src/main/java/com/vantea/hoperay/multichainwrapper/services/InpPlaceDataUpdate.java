package com.vantea.hoperay.multichainwrapper.services;


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
import com.vantea.hoperay.multichainwrapper.services.beans.AssetParamsJSON;
import com.vantea.hoperay.multichainwrapper.services.beans.InpPlaceTcliente;
import com.vantea.hoperay.multichainwrapper.services.beans.InpPlaceTfascicolo;
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

@RestController
@RequestMapping(path = "/InplaceDataUpdate")
public class InpPlaceDataUpdate {
	
	private static Logger log = LogManager.getLogger(InpPlaceDataUpdate.class);
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private MultichainUtils multichainUtils;
	
	@Autowired
	private MultichainServer multichainServer;
	

	public  String userAuthorization = "Inplace" ;
	public  String password ="Inplace987654321";
	
	
	
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
			path = "/Inplace_createUserAddress",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public WrapperReturn newKeyPair(@RequestBody NewKeyPairBody restbody) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchProviderException {
		log.trace("----- Account.newKeyPair -----");
		
	//	if (  restbody.getUserAuthorization().equals(userAuthorization) && restbody.getPassword().equals(password) ) { 
			
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
	
	/*
	@RequestMapping(
			path = "/Inplace_TransferData",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String transferVoucherFrom(@Valid @RequestBody IssueNewVoucherBody body) {
		log.trace("----- Transfer.transferVoucherFrom -----");
		log.debug("BODY: " + body);
		

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
		
		asset.addProperty("Client-ID", body.getCliente_Id());
		asset.addProperty("Client-Stato", body.getCliente_Stato());
		asset.addProperty("Cliente-Codice", body.getCliente_Codice());
		asset.addProperty("cliente-Note", body.getCliente_Note());
		
		
		asset.addProperty("open", Boolean.TRUE);
		log.debug("Asset: " + asset.toString());
		AssetNameJSON assetName = gson.fromJson(asset.toString(), AssetNameJSON.class);

		JsonObject customFields = new JsonObject();
		customFields.addProperty("expire", body.getExpiration().toString());
		customFields.addProperty("value", body.getValue());
	
		customFields.addProperty("Client-ID", body.getCliente_Id());
		customFields.addProperty("Client-Stato", body.getCliente_Stato());
		customFields.addProperty("Cliente-Codice", body.getCliente_Codice());
		customFields.addProperty("cliente-Note", body.getCliente_Note());
		
	
		// add Inplace data 
		log.debug("Custom Fields: " + customFields.toString());
		AssetParamsJSON assetCustomFields = gson.fromJson(customFields.toString(),AssetParamsJSON.class);

		log.debug("AssetParamsJson: " + assetCustomFields.toString());
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
		
		//parse the string jason result 
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
	*/
	
	@RequestMapping(value = "/Inplace_TransferData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String issueNewVoucher(@Valid @RequestBody IssueNewVoucherBody body) throws JsonProcessingException {
		log.trace("----- Issue.issueNewVoucher -----");
		log.trace("BODY: " + body.toString());

		//Check if address has COMPANY Role (low3 on Multichain). Only COMPANY can receive new vouchers
		String receiverRole = multichainUtils.getRoleForAddress(body.getAddress_to());
		log.debug("Receiver " + body.getAddress_to() + " has " + Roles.resolve(receiverRole));
		if (!Roles.resolve(receiverRole).equals(Roles.COMPANY)) {
			throw new WrapperException(HttpStatus.FORBIDDEN, "Address " + body.getAddress_to() + " with role " + Roles.resolve(receiverRole) + " cannot receive new vouchers.");
		}
		
    	Connection conn = null;
    	String issueResponseBody= null;
   	 
        try {
            
            String dbURL = "jdbc:sqlserver://192.168.99.21:1433;databaseName=UAT_DEV_TDE_InplaceCliente_TESTCHAIN;encrypt=true;trustServerCertificate=true;";
            String user = "SA";
            String pass = "zF2fz!u2D-_!Zw_P";
           // String encript = ";encrypt=true;trustServerCertificate=true;";
	       // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
          
            ResultSet rs = null;
            conn = DriverManager.getConnection(dbURL, user, pass);
            Statement stmt = conn.createStatement() ;
   
            InpPlaceTfascicolo inpPlaceTfascicolo  = new InpPlaceTfascicolo();
            Gson gson = new Gson();
    		// Build asset name object
    		JsonObject asset = new JsonObject();
    		asset.addProperty("name", body.getName());
    		asset.addProperty("open", Boolean.TRUE);
    		log.debug("Asset: " + asset.toString());
    		AssetNameJSON assetName = gson.fromJson(asset.toString(), AssetNameJSON.class);

    		// add custom field in details . it can be modified to add more field in future 
    		JsonObject customFields = new JsonObject();
    		JsonArray array = new JsonArray();
            if (conn != null) {
            	
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                rs = stmt.executeQuery("select * from T_Nota");
                ResultSetMetaData rsmd = rs.getMetaData();
                int numbCol = rsmd.getColumnCount();
                log.debug("numbCol" + numbCol);
                while (rs.next()) {
                	 log.debug("cliente_nota_testo" + rs.getString("Nota_Testo"));
            
                }
                log.debug("cliente_nota_testo --------------------add info based on Inplace project-------------------------" );
        		customFields.addProperty("expire", body.getExpiration().toString());
        		customFields.addProperty("value", body.getValue());
        		/*customFields.addProperty("cliente_id", body.getCliente_id());
        		customFields.addProperty("cliente_stato", body.getCliente_stato());
        		customFields.addProperty("cliente_codice", body.getCliente_codice());
        		customFields.addProperty("cliente_note", body.getCliente_note());*/
        		
        		for (int i=1; i<numbCol; i++) {
        			array.add(pass);
        			
        		}
        		
        		 array.add("e-mail: cristian@gmail.com");
        	     array.add("phone: 9848022338");
        	     array.add("city: rome");
        	     array.add("Area: rome");
        	     array.add("State: ostia");
        	  /* while (rs.next()) {
        		   array.add(rs.getString("Nota_Testo"));  
        		   log.debug("The JSONARRAY is here : " + array );
        	   }*/
        	   log.debug("The JSONARRAY is here : " + array );
        	   customFields.add("cliente_nota_testo" , array);
        	   
                
            }
		      	
		log.debug("Custom Fields: " + customFields.toString());
		AssetParamsJSON assetCustomFields = gson.fromJson(customFields.toString(), AssetParamsJSON.class);
		log.debug("assetCustomFields : " + assetCustomFields.toString());
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
		issueResponseBody = issuefromResponse.bodyToMono(String.class).block();
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
		
		
        } catch (SQLException e) {
            System.out.println("unable to connect with SQL DB");
            e.printStackTrace();

        } finally {
        	
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return issueResponseBody; 
        }
       

		
	}
	
	
	
	
	/*
	@RequestMapping(
			path = "/Inplace_TransferData",
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
		params.add(body.getCliente_id());
		params.add(body.getCliente_codice());
		params.add(body.getCliente_stato());
		params.add(body.getCliente_note());
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
	*/

}
