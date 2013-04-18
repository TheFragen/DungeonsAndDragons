package otg;

import java.sql.*;

public class Database {
	
	static int rows = 0;
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
	
	
	public int updateUserInterface() throws Exception {

		int amountRows = getRows("dices");
		int getDiceValue = 0;

		String sGetDiceValue = "SELECT diceValue AS getDice from dices where kastID = " + amountRows;
		String sGetDiceValueFirstRun = "SELECT diceValue AS getDice from dices where kastID = 0";
		

		try {
			String runThis = "Null";
			if (amountRows == 1) {
				execute(sGetDiceValueFirstRun);

				runThis = sGetDiceValueFirstRun;
			} else {
				execute(sGetDiceValue);

				runThis = sGetDiceValue;
			}

			ResultSet rs = executeQuery(runThis);
			try {
				while (rs.next()) {
					int getColoumn = rs.getInt("getDice");
					getDiceValue = getColoumn;
					System.out.println("Value in newest row: " + getColoumn);
				}

			} finally {
				try {
					rs.close();
				} catch (Exception ignore) {
				}
			}
		} finally {
			try {
				
			} catch (Exception ignore) {
			}
		}
		return getDiceValue;
	}

	
	public int getRows(String s) throws Exception {
		String sMakeUpdate = "SELECT COUNT(*) AS rowNumber FROM " + s;

		try {
			execute(sMakeUpdate);

			ResultSet rs = executeQuery(sMakeUpdate);

			try {
				while (rs.next()) {
					int sResult = rs.getInt("rowNumber");
					rows = sResult;
					System.out.println("Number of rows in " + s + " is: "+ rows);

				}
			} finally {
				try {
					rs.close();
				} catch (Exception ignore) {
				}
			}
		} finally {
			try {
			
			} catch (Exception ignore) {
			}
		}

		return rows;
	}
	
	public String getActiveUser() throws Exception {
		String currentActive = "No one";
		String sActivePlayer = "SELECT userName AS activeUser from users where isTurn = 1";

		try {
			execute(sActivePlayer);
			ResultSet rs = executeQuery(sActivePlayer);
			try {
				while (rs.next()) {
					String getActiveUser2 = rs.getString("activeUser");
					currentActive = getActiveUser2;
					System.out.println("Active user is: " +getActiveUser2);
				}

			} finally {
				try {
					rs.close();
				} catch (Exception ignore) {
				}
			}
		} finally {
			try {
			} catch (Exception ignore) {
			}
		}
		return currentActive;
	}

}