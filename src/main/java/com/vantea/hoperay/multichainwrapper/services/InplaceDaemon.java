package com.vantea.hoperay.multichainwrapper.services;

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

public class InplaceDaemon extends Thread {

	private static Logger log = LogManager.getLogger(InplaceDaemon.class);
	private static final String PROTOCOL_HTTP = "HTTP/1.1";
	private static final String SCHEME_HTTP = "http";

	private static final int PORT = 2751;
 

	private static Map<String, String> dictionary = new HashMap<String, String>();

	public void daemonrun() throws IOException {
		 int port = 2751;
		 
	        try (ServerSocket serverSocket = new ServerSocket(port)) {
	 
	            log.debug("Server is listening on port " + port);
	 
	            while (true) {
	                Socket socket = serverSocket.accept();
	                log.debug("New client connected");
	 
	                InputStream input = socket.getInputStream();
	                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	 
	                OutputStream output = socket.getOutputStream();
	                PrintWriter writer = new PrintWriter(output, true);
	 
	 
	                String text;
	                
	 
	                do {
	                    text = reader.readLine();
	                    String reverseText = new StringBuilder(text).reverse().toString();
	                    log.debug("Server: " + reverseText);
	 
	                } while (!text.equals("bye"));
	 
	                socket.close();
	            }
	 
	        } catch (IOException ex) {
	        	 log.debug("Server exception: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	}

	private  void serveClient(Socket socket) throws IOException {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
						true);) {
			while (true) {
				String str = in.readLine();
				if (str.equals("END"))
					break;
				log.debug("Echoing: " + str);
				log.debug(str);
			}
		}
	}

}

/*
 * if(Thread.currentThread().isDaemon()){//checking for daemon thread
 * System.out.println("daemon thread work"); } else{
 * System.out.println("user thread work"); }
 * 
 * 
 * InetSocketAddress addr = new InetSocketAddress("192.168.99.72",
 * LISTENING_PORT); HttpServer serverRemote = HttpServer.create(addr, 0);
 * 
 * 
 * 
 * Connector connector = new Connector(PROTOCOL_HTTP);
 * connector.setScheme(SCHEME_HTTP); connector.setPort(LISTENING_PORT);
 * connector.setSecure(false);
 * 
 * 
 * log.debug("daemon Connector: " + connector );
 * log.debug("daemon HTTP server: " + serverRemote );
 * 
 * serverRemote.setExecutor(Executors.newCachedThreadPool());
 * serverRemote.start(); System.out.println("Server is listening on port 2751"
 * );
 * 
 * // connection using socket
 * 
 * String server = "192.168.99.72"; String path = "http://192.168.99.72:2751/";
 * 
 * log.debug( "Loading contents of URL: " + server );
 * 
 * try { // Connect to the server Socket socket = new Socket( server, 2751 );
 * 
 * // Create input and output streams to read from and write to the server
 * PrintStream out = new PrintStream( socket.getOutputStream() ); BufferedReader
 * in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
 * 
 * 
 * 
 * // Follow the HTTP protocol of GET <path> HTTP/1.0 followed by an empty line
 * log.debug( "GET " + path + " HTTP/1.0" );
 * 
 * 
 * // Read data from the server until we finish reading the document String line
 * = in.readLine(); log.debug("line: "+ line); while( line != null ) {
 * 
 * System.out.println( line ); line = in.readLine(); }
 * 
 * InplaceValidateToken inplaceValidateToken = new InplaceValidateToken();
 * 
 * inplaceValidateToken.inplaceValidateToken(line);
 * 
 * log.debug("inplaceValidateToken.inplaceValidateToken(line);: "+ line); //
 * Close our streams in.close(); out.close(); socket.close(); } catch( Exception
 * e ) { e.printStackTrace(); } }
 */
