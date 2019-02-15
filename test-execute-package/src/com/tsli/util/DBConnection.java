package com.tsli.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import oracle.jdbc.OracleDriver;

import com.tmax.tibero.jdbc.TbDriver;

public class DBConnection {
	private static DataSource dataSource = null;

    private static String server = "";
    private static String jndiDatasource = "";
    
    public static Connection getConnection() throws Exception {
    	server = ConfigBundle.getValue("DEFAULT_SERVER");
    	if ("ORACLE".equals(server)) {
    		jndiDatasource = ConfigBundle.getValue("JNDI_ORACLE_DATASOURCE");
    	}else{
    		jndiDatasource = ConfigBundle.getValue("JNDI_TIBERO_DATASOURCE");
    	}
    	return getConnection(jndiDatasource);
    }

    private static Connection getConnection(String jndi) throws SQLException, NamingException {
        Connection conn = null;

        if ("TOMCAT".equals(server)) {
            conn = DBConnection.getTomcatDataBaseConnection(jndi);
        } else if ("WEBSPHERE".equals(server)) {
            conn = DBConnection.getWas6DataBaseConnection(jndi);
        } else if ("JBOSS".equals(server)) {
            conn = DBConnection.getJbossDataBaseConnection(jndi);
        } else if ("OC4J".equals(server)) {
            conn = DBConnection.getOC4JDataBaseConnection(jndi);
        }else if ("TIBERO".equals(server)) {
            conn = DBConnection.getTiberoDataBaseConnection(jndi);
        }else if ("ORACLE".equals(server)) {
            conn = DBConnection.getOracleDataBaseConnection(jndi);
        }


        conn.setAutoCommit(false);
        return conn;
    }
    /**
     * Get Database Connection form Database pooling
     * (Only for Websphere pooling management)
     *
     * @param dbName String
     * @return Connection
     * @throws Exception
     */
    private static Connection getWas5DataBaseConnection(String jndi) throws Exception {
        Connection conn = null;
        //System.out.println("WEBSPHERE5");
        try {
            if (dataSource == null) {
                Context ctx = null;
                Hashtable env = new Hashtable();
                env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
                ctx = new InitialContext(env);
                dataSource = (javax.sql.DataSource) ctx.lookup(jndi);
                ctx.close();
            }
            conn = dataSource.getConnection();
        } catch (Exception ex) {
            throw new SQLException("Error WEBSPHERE 5 " + jndi + " connection: " + ex.getMessage());
        }
        return conn;
    }

    private static Connection getWas6DataBaseConnection(String jndi) throws SQLException, NamingException {
        Connection conn = null;
        //System.out.println("WEBSPHERE6");
        try {
            if (dataSource == null) {
                Context ctx = null;
                ctx = new InitialContext();
                dataSource = (DataSource)ctx.lookup(jndi);
                ctx.close();
            }
            conn = dataSource.getConnection();
        } catch (SQLException ex) {
            throw new SQLException("Error WEBSPHERE 6 " + jndi + " connection: " + ex.getMessage());
        }
        return conn;
    }

    /**
     * Get Database Connection form Database pooling
     * (Only for JBoss pooling management)
     *
     * @param jndi String
     * @return Connection
     * @throws NamingException 
     * @throws Exception
     */
    private static Connection getJbossDataBaseConnection(String jndi) throws SQLException, NamingException {
        Connection conn = null;
        try {
            if(dataSource == null) {
                InitialContext ctxt = new InitialContext();
                dataSource = (DataSource)ctxt.lookup("java:" + jndi);
                ctxt.close();
            }
            conn = dataSource.getConnection();
        } catch(SQLException ex) {
            throw new SQLException("Error Process Connection " + jndi + " connection: " + ex.getMessage());
        }
        return conn;
    }

    /**
     * Get dababase connection polling
     * (Only for Apache Tomcat pooling management)
     *
     * @param jndi String
     * @return Connection
     * @throws NamingException 
     * @throws Exception
     */
    private static Connection getTomcatDataBaseConnection(String jndi) throws SQLException, NamingException {
        Connection conn = null;
        try {
            if(dataSource == null) {
                InitialContext ctxt = new InitialContext();
                dataSource = (DataSource)ctxt.lookup("java:/comp/env/" + jndi);
                ctxt.close();
            }
            conn = dataSource.getConnection();
        } catch(SQLException ex) {
            throw new SQLException("Error Process Connection " + jndi + " connection: " + ex.getMessage());
        }

        return conn;
    }

    private static Connection getOC4JDataBaseConnection(String jndi) throws SQLException, NamingException {
        Connection conn = null;
        try {
            if(dataSource == null) {
                InitialContext ctxt = new InitialContext();
                dataSource = (DataSource)ctxt.lookup(jndi);
                ctxt.close();
            }
            conn = dataSource.getConnection();
        } catch(SQLException ex) {
            throw new SQLException("Error Process Connection " + jndi + " connection: " + ex.getMessage());
        }

        return conn;
    }

    private static Connection getTiberoDataBaseConnection(String jndi) throws SQLException, NamingException {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new TbDriver());
            conn = DriverManager.getConnection( ConfigBundle.getValue("JDBC_TBR_URL"),  ConfigBundle.getValue("JDBC_TBR_USERNAME"),  ConfigBundle.getValue("JDBC_TBR_PASSWORD"));
        } catch(SQLException ex) {
            throw new SQLException("Error Process Connection " + jndi + " connection: " + ex.getMessage());
        }

        return conn;
    }
    

    private static Connection getOracleDataBaseConnection(String jndi) throws SQLException, NamingException {
        Connection conn = null;
        try {
        	DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection( ConfigBundle.getValue("JDBC_ORC_URL"),  ConfigBundle.getValue("JDBC_ORC_USERNAME"),  ConfigBundle.getValue("JDBC_ORC_PASSWORD"));
        } catch(SQLException ex) {
            throw new SQLException("Error Process Connection " + jndi + " connection: " + ex.getMessage());
        }

        return conn;
    }
    /*
    public static Connection loadStaticConnection() throws SQLException, Exception {

        Connection conn = null;

        if(bds==null) {
             bds = new BasicDataSource();
            try {

                bds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
                bds.setUrl("jdbc:db2://172.19.19.177:50001/essswcf");
                bds.setUsername("essswcf");
                bds.setPassword("essswcf");

//                bds.setUrl("jdbc:db2://server1/WCF");
//                bds.setUsername("essswcf");
//                bds.setPassword("essswcf");

            } catch (Exception ex){
                throw new Exception(ex.getMessage());
            }
        }

        try {

            conn = bds.getConnection();
        } catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        return conn;

}*/


    /**
     * Release Database connection session and return to database pooling
     *
     * @param conn Connection
     * @throws Exception
     */
    public static void closeConnection(Connection conn) throws SQLException {
        try {
        	if(conn != null){
        		if (!conn.isClosed()) {
                    conn.close();
                }
        	}
        } catch (SQLException e) {
            throw new SQLException("Error Close Connection: " + e.getMessage());
        }
    }


    public static void setServerName(String name) throws Exception {
        try {
            server = name.toUpperCase();
        } catch (Exception e) {
            throw e;
        }
    }
}
