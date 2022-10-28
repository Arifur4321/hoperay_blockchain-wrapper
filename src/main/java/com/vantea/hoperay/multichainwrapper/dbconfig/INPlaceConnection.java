package com.vantea.hoperay.multichainwrapper.dbconfig;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Statement;

import com.vantea.hoperay.multichainwrapper.services.beans.InpPlaceTfascicolo;
 
/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *
 */
public class INPlaceConnection {
 
    public INPlaceConnection()  
    
    {  
    	
    }
    
    public Connection   sqlconnection () {
    	Connection conn = null;
    	 
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
            
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                rs = stmt.executeQuery("select * from T_Nota");
                
                
                while (rs.next()) {
                    System.out.println(rs.getString("Nota_Testo"));
                   /* 
                    inpPlaceTfascicolo.setFascicolo_Codice(rs.getString("Fascicolo_Codice"));
                    System.out.println("inpPlaceTfascicolo.getFascicolo_Codice() : " +inpPlaceTfascicolo.getFascicolo_Codice());
                    inpPlaceTfascicolo.setFascicolo_MetaData(rs.getString("Fascicolo_MetaData"));
                    System.out.println( "inpPlaceTfascicolo.Fascicolo_MetaData() : " + inpPlaceTfascicolo.getFascicolo_MetaData()); 
                   */
                }
                
                
                System.out.println("connected with inPlace SQL DB : " + rs);
                System.out.println("iNPLaceDriver name: " + dm.getDriverName());
                System.out.println("iNPlace Driver version: " + dm.getDriverVersion());
                System.out.println("iNPlace Product name: " + dm.getDatabaseProductName());
                System.out.println("iNPlace Product version: " + dm.getDatabaseProductVersion());
                System.out.println("iNPlace conn data: " + conn);
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
        }
		return conn;
		
		
    }
    
}