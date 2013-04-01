package otg;

public class DatabaseRows {
	
//	public static int getRows() throws Exception {
//		String sDriver = "jdbc:sqlite";
//		String sDatabaseToUse = "diceRolls.db";
//		String sDbUrl = sDriver + ":" + sDatabaseToUse;
//		Database db = new Database(sDbUrl);
//
//		String sMakeUpdate = "SELECT COUNT(*) AS rowNumber FROM dices";
//
//		try {
//			db.execute(sMakeUpdate);
//
//			ResultSet rs = db.executeQuery(sMakeUpdate);
//
//			try {
//				while (rs.next()) {
//					int sResult = rs.getInt("rowNumber");
//					id1 = sResult;
//					rows = sResult;
//					System.out.println("Number of rows " + sResult + " ");
//
//				}
//			} finally {
//				try {
//					rs.close();
//				} catch (Exception ignore) {
//				}
//			}
//		} finally {
//			try {
//				((ResultSet) db).close();
//			} catch (Exception ignore) {
//			}
//		}
//		
//		return rows;
//	}

}
