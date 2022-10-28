package com.vantea.hoperay.multichainwrapper.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.URLConnection;
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
import com.vantea.hoperay.multichainwrapper.services.beans.AssetParamJsonCambioStatoFasciolo;
import com.vantea.hoperay.multichainwrapper.services.beans.AssetParamsJSON;
import com.vantea.hoperay.multichainwrapper.services.beans.AssetParamsJsonMessageNuovo;
import com.vantea.hoperay.multichainwrapper.services.beans.InpPlaceTcliente;
import com.vantea.hoperay.multichainwrapper.services.beans.InpPlaceTfascicolo;
import com.vantea.hoperay.multichainwrapper.services.beans.input.Cambiostatofascicolobean;
import com.vantea.hoperay.multichainwrapper.services.beans.input.InplaceMessagebeanNuovo;
import com.vantea.hoperay.multichainwrapper.services.beans.input.InplaceTokenValidatebean;
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
@RequestMapping(value = "/Inplace_Token")
public class InplaceValidateToken {

	private static Logger log = LogManager.getLogger(InpPlaceDataUpdate.class);
	
	public  String userAuthorization = "Inplace" ;
	public  String password ="Inplace987654321";
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private MultichainUtils multichainUtils;
	
	@Autowired
	private MultichainServer multichainServer;
	
	boolean ValidazioneOK ;
	
	//@Valid @RequestBody InplaceTokenValidatebean body
	//@RequestMapping(value = "/Inplace_ValidateToken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void inplaceValidateToken( String uri ) throws Exception {
		log.trace("----- Token Validate -----");
     
		
		//the iNplace service will be here 
		//final String uri = "http://192.168.99.21:8189/InplaceAPI/InplaceAPI_ValidazioneToken/EseguiValidazioneToken?classifaitoken=";
	    
   	   // String toSplit = "http://192.168.99.21:8189/InplaceAPI/InplaceAPI_ValidazioneToken/EseguiValidazioneToken?valoreToken="
	    		
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
		//get the token value 
	    String valoreToken = uri.substring(uri.lastIndexOf("=") + 1);
	    
	    log.debug(valoreToken);
	    
        log.debug(result);
		
        if ( valoreToken.equals("ValidazioneOK"))
        {      	 
			try {
				

	        	URL myURL = new URL("http://192.168.99.72:2751");
	            URLConnection myURLConnection = myURL.openConnection();
	            myURLConnection.connect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          
        }else {
        	
        	
        throw new Exception(result);
        }
       
        
      
	}

}
