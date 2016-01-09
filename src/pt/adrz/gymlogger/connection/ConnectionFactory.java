package pt.adrz.gymlogger.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
	
	public static final String CONTEXT_STRING = "java:/comp/env";
	public static final String DATASOURCE_STRING = "jdbc/gymlogger";
	public static final String DATASOURCE_NAME = "java:comp/env/jdbc/gymlogger";
	
	public static final String DB_URL = "jdbc:mysql://localhost:3306/";
	public static final String DATABASE = "gymlogger";
	public static final String USER = "root";
	public static final String PASS = "";
	
	private static ConnectionFactory instance = null;
	
	private static DataSource src;
	
	private ConnectionFactory() {

		try {
			Context ctx = new InitialContext();
			src = (DataSource)ctx.lookup(DATASOURCE_NAME);
		}
		catch (NamingException eN) {
			//eN.printStackTrace();
		}
	}
	
	public static ConnectionFactory getInstance() {

		if ( ConnectionFactory.instance == null ) { 
			ConnectionFactory.instance = new ConnectionFactory(); 
		}

		return ConnectionFactory.instance;
	}
	
	public static Connection getConnection() throws SQLException {
		
		Connection conn = null; 

		if ( instance == null ) { 
			instance = new ConnectionFactory(); 
		}
		
		try {

			if ( src == null ) { // conn to use without web server
				conn = DriverManager.getConnection(DB_URL + DATABASE, USER, PASS);
			}
			else {
				conn = src.getConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return conn;
	}
	
	public static DataSource getDataSource() {
		return src;
	}
	
	public static void close(ResultSet rs) {
		if ( rs == null) return;
		try { rs.close(); }
		catch (SQLException eSQL) {}
	}

	public static void close(Statement st) {
		if ( st  == null) return;
		try { st.close(); }
		catch (SQLException eSQL) {}
	}
	
	public static void close(PreparedStatement ps) {
		if ( ps == null) return;
		try { ps.close(); }
		catch (SQLException eSQL) {}
	}

	public static void close(Connection conn) {
		if ( conn == null) return;
		try { conn.close(); }
		catch (SQLException eSQL) {}
	}
	
	public static void close(ResultSet rs, Statement st, Connection conn) {
		close(rs);
		close(st);
		close(conn);
	}

	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		close(rs);
		close(ps);
		close(conn);
	}
	
	public static void close(ResultSet rs, Statement st, PreparedStatement ps, Connection conn) {
		close(rs);
		close(st);
		close(ps);
		close(conn);
	}
}
