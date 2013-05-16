package otg;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Random;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Lobby extends JPanel{
	
	private static final long serialVersionUID = 1364856722231740218L;
	
	
	DefaultTableModel model = new DefaultTableModel(); 
	String sDriver = "jdbc:sqlite:lobbies.db";
	Database db = new Database(sDriver);
	boolean firstClick = true;
	String lobbyDatabase = "Null";
	MainWindow mw;
	
	int updateHPRows = 0;
	private JScrollPane scrollPane;
	
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Lobby(MainWindow mw) throws Exception{
		this.mw = mw;
		initialize();
		getGames();
	}
		
	
	public void initialize() throws Exception {
		databaseConnection();
		
		setLayout(null);
		
		JLabel lblLobbies = new JLabel("Lobbies");
		lblLobbies.setHorizontalAlignment(SwingConstants.CENTER);
		lblLobbies.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLobbies.setBounds(10, 11, 238, 25);
		add(lblLobbies);
		
		table = new JTable(model){  
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){  
			    return false;  
			  }  
				};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(false);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setEnabled(true);
		table.setBounds(155, 257, 92, 128);
		table.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
		         if (e.getClickCount() == 2){
		        	JTable target = (JTable)e.getSource();
		        	int selectedRow = target.getSelectedRow();
		            boolean acceptedInteger = false;
		            int healthPoints = 0;
		            while(!acceptedInteger){
						try {
							String hpString = JOptionPane.showInputDialog(null,"Please input amount of health points (leave blank if none):","Health points",1);
								healthPoints = Integer.parseInt(hpString);
								acceptedInteger = true;
								try {
									JOptionPane.showMessageDialog(null,"Health points has been updated");
									db.execute("UPDATE images SET hitPoints = "+healthPoints +" WHERE imageID = " +selectedRow);
									model.setValueAt(hpString, selectedRow, 1);
									model.fireTableRowsUpdated(1, selectedRow);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								
						} catch (NumberFormatException e2) {
							JOptionPane.showMessageDialog(null,"Please only use numbers");
						}
					}     
		            }
		         }
		        });
		model.addColumn("Name");
		model.addColumn("Host");
		model.addColumn("Players");
		table.getColumnModel().getColumn(0).setPreferredWidth(180);
		add(table);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 57, 230, 321);
		add(scrollPane);
		
		
		// Knap til at oprette spil og skifte til DM ui
		JButton btnHostGame = new JButton("Host");
		btnHostGame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					int i = 0;
					if(firstClick == true){
						i = db.getRows("lobby");
						firstClick = false;
					}else {
						i = getNewestGame() + 1;
					}
					
					
					String gameName = JOptionPane.showInputDialog(null,"Please input your games name","Game Name",1); // Sæt spil navn
					String hostName = JOptionPane.showInputDialog(null,"Please input your own name","Host name",1); // Sæt hostens navn
					if(gameName == null || hostName == null){
						JOptionPane.showMessageDialog(null, "Cancelled"); //Bliver der ikke skrevet noget i en af inputdialogerne canceller vi host operationen
					} else {

						char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray(); // Lav en random liste af bogstaver som navn til database
						StringBuilder sb = new StringBuilder();
						Random random = new Random();
						for (int o = 0; o < 8; o++) {
							char c = chars[random.nextInt(chars.length)];
							sb.append(c);
						}
						String output = sb.toString();
						db.execute("INSERT INTO lobby VALUES (" + i + ",'" + gameName + "','" + hostName + "'," + 0 + ",'" + output + ".db" + "')");
						db.closeConnection();
						String sDriver = "jdbc:sqlite:" + output + ".db";
						Database db = new Database(sDriver);
						DungeonMasterUI dm = new DungeonMasterUI(db);
						dm.setsDriver(output + ".db");
						mw.dungeonmasterCard(dm);
						dm.setGamename(gameName);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnHostGame.setBounds(10, 389, 62, 23);
		add(btnHostGame);
		
		
		// Knap til at tilslutte spil
		JButton btnJoinGame = new JButton("Join");
		btnJoinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnJoinGame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
		        Object sGamename = table.getValueAt(row, 0);
		        System.out.println("Value at row "+row + " is: " +sGamename);
		        
				try {
					String sDriver = "jdbc:sqlite:" +getDatabaseName(row); //Anskaf navnet på den database vi vil bruge, altså kolonne 5 i table lobby
					int newPlayerCount = getPlayerCount() + 1;
					db.execute("UPDATE lobby SET playerCount = " + newPlayerCount + " WHERE gameID = '" + row + "'");
					Database db2 = new Database(sDriver);
					PlayerUI player = new PlayerUI(db2, mw);
					player.setThreadRunning(true);
					mw.playerCard(player);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnJoinGame.setBounds(80, 389, 62, 23);
		add(btnJoinGame);
		
		//Genopfrisk listen med spil
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					getGames();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setBounds(157, 389, 79, 23);
		add(btnRefresh);
	}
	
	
	public void databaseConnection() throws Exception {

//		String sDropTable = "DROP TABLE IF EXISTS lobby";
		String sMakeTable = "CREATE TABLE if NOT EXISTS lobby (gameID INT IDENTITY PRIMARY KEY, gameName text, hostName text, playerCount numeric, databaseName text)";

		try {

//			db.execute(sDropTable);
			db.execute(sMakeTable);

		} finally {
			try {
			} catch (Exception ignore) {
			}
		}
	}
	
	
	public void getGames() throws Exception {
		int rows = getNewestGame() + 1;
		
		while (model.getRowCount() < rows){
			String sGetElements = "SELECT gameName AS getGames from lobby where gameID = " +updateHPRows ;
			
			
			try {

				db.execute(sGetElements);
				ResultSet rs = db.executeQuery(sGetElements);

				try {
					while (rs.next()) {
						String getGames = (rs.getString("getGames"));	
						if(model.getRowCount() < rows ){
							model.addRow(new Object[]{getGames});
						}
							
						getPlayerCount();
						getHostName();
						
						
					}
				} finally {
					try {
						rs.close();
					} catch (Exception ignore) {
					}
				}
			} finally {
				try {
					((ResultSet) db).close();
				} catch (Exception ignore) {
				}
			}updateHPRows++;
		}
	}
	
	public int getNewestGame() throws Exception {
		int gameID = 0;
		
		String sMakeUpdate = "SELECT gameID AS newestValue FROM lobby WHERE gameID = (SELECT MAX(gameID)  FROM lobby)";

		try {
			db.execute(sMakeUpdate);

			ResultSet rs = db.executeQuery(sMakeUpdate);

			try {
				while (rs.next()) {
					gameID = rs.getInt("newestValue");

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
		
		return gameID;
	}
	
	
	
	public void getHostName() throws Exception {
		int rows = model.getRowCount();
		
		for(int i = 0; i < rows; i++){
			String sGetHostName = "SELECT hostName AS getHost from lobby where gameID = " +i;
				
			try {

				db.execute(sGetHostName);
				ResultSet rs = db.executeQuery(sGetHostName);

				try {
					while (rs.next()) {
						String getHost = rs.getString("getHost");
						model.setValueAt(getHost, i, 1);
					}
				} finally {
					try {
						rs.close();
					} catch (Exception ignore) {
					}
				}
			} finally {
				try {
					((ResultSet) db).close();
				} catch (Exception ignore) {
				}
			}
		}	
	}
	
	public int getPlayerCount() throws Exception {
		int rows = model.getRowCount();
		int returnPlayers = 0;
		
		for(int i = 0; i < rows; i++){
			String sGetPlayers = "SELECT playerCount AS getPlayers from lobby where gameID = " +i;
				
			try {

				db.execute(sGetPlayers);
				ResultSet rs = db.executeQuery(sGetPlayers);

				try {
					while (rs.next()) {
						int getPlayers = rs.getInt("getPlayers");
						String players = Integer.toString(getPlayers);
						returnPlayers = getPlayers;
						model.setValueAt(players, i, 2); // set værdi på tabelen til hvad database har meldt tilbage ved getPlayers
					}
				} finally {
					try {
						rs.close();
					} catch (Exception ignore) {
					}
				}
			} finally {
				try {
					((ResultSet) db).close();
				} catch (Exception ignore) {
				}
			}
		}
		return returnPlayers;	
	}
	
	public String getDatabaseName(int gameID) throws Exception {

		String database = "Null";
		String sDatabase = "SELECT databaseName AS getDatabase from lobby where gameID = " +gameID;
		System.out.println(sDatabase);

		try {

			db.execute(sDatabase);
			ResultSet rs = db.executeQuery(sDatabase);

			try {
				while (rs.next()) {
					database = rs.getString("getDatabase");
				}
			} finally {
				try {
					rs.close();
				} catch (Exception ignore) {
				}
			}
		} finally {
			try {
				((ResultSet) db).close();
			} catch (Exception ignore) {
			}
		}
		return database;
	}	
}
