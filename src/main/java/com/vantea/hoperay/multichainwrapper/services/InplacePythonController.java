package com.vantea.hoperay.multichainwrapper.services;

import java.io.IOException;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.io.InputStream;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import org.xml.sax.InputSource;

import java.io.*;
import java.net.*;

import java.util.concurrent.Executors;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;

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
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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


public class InplacePythonController {

	private static Logger log = LogManager.getLogger(InplaceDaemon.class);
	private static final String PROTOCOL_HTTP = "HTTP/1.1";
	private static final String SCHEME_HTTP = "http";

	private static final int PORT = 2751;
 

	private static Map<String, String> dictionary = new HashMap<String, String>();

	
	public void pythonCheck() throws IOException {
		
		 String pythonScriptPath = "/home/pi/test.py";
	        String[] cmd = { "python", pythonScriptPath };

	        // create runtime to execute external command
	        ProcessBuilder pb = new ProcessBuilder(cmd);

	        // retrieve output from python script       
	        pb.redirectError();

	        while(true){
	            Process p = pb.start();
	            log.debug("Process Started...");
	            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
	            String str = in.readLine();
	            log.debug("value is : "+str);
	        }
	}
}
