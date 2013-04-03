package otg;

import java.sql.*;

public class Database {
	public String sUrl;
	private String sDriverName = "org.sqlite.JDBC";
	private String sDriver;
	private Connection conn = null;
	private Statement stmt = null;

	public Database(String sDbUrl) throws Exception {
		sUrl = sDbUrl;
		setConnection();
	}

	private void setConnection() throws Exception {
		try {
			Class.forName(sDriverName);
		} catch (Exception e) {
			// Exception til "Connection Failed" 
			System.out.println("DriverName: " + sDriver + " was not available");
			System.err.println(e);
			throw e;
		}
		// Opret forbindelse
		conn = DriverManager.getConnection(sUrl);
		try {
			stmt = conn.createStatement();
		} catch (Exception e) {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
			conn = null;
		}
	}

	public void closeConnection() {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception ignore) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	}

	// Nem måde til at execute SQL
	public ResultSet executeQuery(String instruction) throws SQLException {
		return stmt.executeQuery(instruction);
	}

	public void execute(String instruction) throws SQLException {
		stmt.executeUpdate(instruction);
	}

}