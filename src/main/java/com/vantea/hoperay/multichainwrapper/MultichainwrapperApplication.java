package com.vantea.hoperay.multichainwrapper;

 
import java.io.IOException;
import java.security.Security;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import  com.vantea.hoperay.multichainwrapper.dbconfig.INPlaceConnection;
import com.vantea.hoperay.multichainwrapper.services.InplaceDaemon;
import com.vantea.hoperay.multichainwrapper.services.InplacePythonController;

@SpringBootApplication
public class MultichainwrapperApplication {

 
	public static void main(String[] args) throws IOException {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		SpringApplication.run(MultichainwrapperApplication.class, args);
		//INPlaceConnection sqlconn = new INPlaceConnection();
		//sqlconn.sqlconnection();
		//InplaceDaemon inplaceDaemon = new InplaceDaemon();
		
		InplacePythonController inplacePythonController = new InplacePythonController();
		
		inplacePythonController.pythonCheck();
	}

 
}
