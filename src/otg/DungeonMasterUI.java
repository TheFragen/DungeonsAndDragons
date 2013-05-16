package otg;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.sql.ResultSet;
import javax.swing.SwingConstants;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class DungeonMasterUI extends JPanel {

	private static final long serialVersionUID = 2607816291271048284L;
	
	FTP ftp = new FTP();
	
	int playersRolling = 0;
	int rows = 0;
	private JLabel lblrolledValue;
	private JLabel lblPlayernames;
	private JLabel lblDices;
	private JLabel lblGamename;
	final JFileChooser fc = new JFileChooser();
	String currentUser = "Null";
	String current = "None";
	boolean imageActive = false;
	private JScrollPane scrollPane;
	Database db;
	boolean firstClick = true;
	int lastValue = 0;
	int updateHPRows = 0;

	String user;
	boolean threadRunning = true;
	int setButtons = 0;
	
	
	
	JToggleButton btnPlayerone;
	JToggleButton btnPlayertwo;
	JToggleButton btnPlayerthree;
	JToggleButton btnPlayerfour;
	JToggleButton btnPlayerfive;
	JToggleButton btnPlayersix;
	DefaultTableModel model = new DefaultTableModel(); 
	
	String databaseDriver = "Null";
	
	public void setsDriver(String sDriver) {
		this.databaseDriver = sDriver;
	}
	
	public void setThreadRunning(boolean threadRunning) {
		this.threadRunning = threadRunning;
	}

	
	
	private JTable table;

	public DungeonMasterUI(Database db) throws Exception{
		this.db = db;
		
		databaseConnection();
		initialize();
		
		Thread feedDiceValue = new Thread(new Runnable() {
			public void run() {

				for (int i = 0; i == 0; i = 0) {
					try {
							feedValue();
							getUser(); // Updater knapper næsten real time i tilfælde af spillere joiner et rum
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
			
			feedDiceValue.start();
			
	}
		
		public void initialize() throws Exception {
		
		//Her initialiseres programmet
			
		lblGamename = new JLabel("Attack of the Swedes");
		lblGamename.setBounds(10, 11, 236, 20);
		lblGamename.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String s = JOptionPane.showInputDialog(null, "Please enter you game name : ", "Game name", 1);
				lblGamename.setText(s);
			}
		});
		setLayout(null);

		table = new JTable(model) {
			private static final long serialVersionUID = 4352633901210885218L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		//Opdater tabel med ny healthpoint
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(false);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setEnabled(false);
		table.setVisible(false);
		table.setBounds(155, 257, 92, 128);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int selectedRow = target.getSelectedRow();
					boolean acceptedInteger = false;
					int healthPoints = 0;
					while (!acceptedInteger) {
						try {
							String hpString = JOptionPane.showInputDialog(	null,"Please input amount of health points (leave blank if none):", "Health points", 1);
							healthPoints = Integer.parseInt(hpString);
							acceptedInteger = true;
							try {
								JOptionPane.showMessageDialog(null, "Health points has been updated");
								int rowToChange = selectedRow;
								db.execute("UPDATE images SET hitPoints = " + healthPoints + " WHERE imageID = "+ rowToChange);
								model.setValueAt(hpString, selectedRow, 1);
								model.fireTableRowsUpdated(1, selectedRow);
							} catch (Exception e1) {
								e1.printStackTrace();
							}

						} catch (NumberFormatException e2) {
							JOptionPane.showMessageDialog(null, "Please only use numbers");
						}
					}
				}
			}
		});
		model.addColumn("Name");
		model.addColumn("HP");
		add(table);

		lblGamename.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamename.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblGamename);

		JPanel playerPanel = new JPanel();
		playerPanel.setBounds(10, 56, 236, 95);
		add(playerPanel);

		//Knap for spiller 1
		btnPlayerone = new JToggleButton("playerOne");
		btnPlayerone.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (btnPlayerone.isSelected() == true) {
					try {
						if(playersRolling == 1){
							JOptionPane.showMessageDialog(null,"Only one player at a time can roll");
						} else {
							setActiveUser(btnPlayerone.getText(), 1);
							playersRolling = 1;
						}					
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						setActiveUser(btnPlayerone.getText(), 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});			
		btnPlayerone.setVisible(false);
		playerPanel.add(btnPlayerone);

		//Knap for spiller 2
		btnPlayertwo = new JToggleButton("playerTwo");
		btnPlayertwo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (btnPlayertwo.isSelected() == true) {
					try {
						if(playersRolling == 1){
							JOptionPane.showMessageDialog(null,"Only one player at a time can roll");
						} else {
						setActiveUser(btnPlayertwo.getText(), 1);
						playersRolling = 1;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						setActiveUser(btnPlayertwo.getText(), 0);			
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});
		btnPlayertwo.setVisible(false);
		playerPanel.add(btnPlayertwo);

		//Knap for spiller 3
		btnPlayerthree = new JToggleButton("playerThree");
		btnPlayerthree.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (btnPlayerthree.isSelected() == true) {
					try {	
						if (playersRolling == 1) {
							JOptionPane.showMessageDialog(null, "Only one player at a time can roll");
						} else {
							setActiveUser(btnPlayerthree.getText(), 1);
							playersRolling = 1;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {					
						setActiveUser(btnPlayerthree.getText(), 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});			
		btnPlayerthree.setVisible(false);
		playerPanel.add(btnPlayerthree);

		//Knap for spiller 4
		btnPlayerfour = new JToggleButton("playerFour");
		btnPlayerfour.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (btnPlayerfour.isSelected() == true) {
					try {			
						if(playersRolling == 1){
							JOptionPane.showMessageDialog(null,"Only one player at a time can roll");
						} else {
						setActiveUser(btnPlayerfour.getText(), 1);
						playersRolling = 1;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {					
						setActiveUser(btnPlayerfour.getText(), 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});	
		btnPlayerfour.setVisible(false);
		playerPanel.add(btnPlayerfour);

		//Knap for spiller 5
		btnPlayerfive = new JToggleButton("playerFive");
		btnPlayerfive.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (btnPlayerfive.isSelected() == true) {
					try {	
						if(playersRolling == 1){
							JOptionPane.showMessageDialog(null,"Only one player at a time can roll");
						} else {
						setActiveUser(btnPlayerfive.getText(), 1);
						playersRolling = 1;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {					
						setActiveUser(btnPlayerfive.getText(), 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});
		btnPlayerfive.setVisible(false);
		playerPanel.add(btnPlayerfive);

		//Knap for spiller 6
		btnPlayersix = new JToggleButton("playerSix");
		btnPlayersix.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (btnPlayersix.isSelected() == true) {
					try {	
						if(playersRolling == 1){
							JOptionPane.showMessageDialog(null,"Only one player at a time can roll");
						} else {
						setActiveUser(btnPlayersix.getText(), 1);
						playersRolling = 1;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {					
						setActiveUser(btnPlayersix.getText(), 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});			
		btnPlayersix.setVisible(false);
		playerPanel.add(btnPlayersix);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(false);
		scrollPane.setVisible(false);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(88, 272, 155, 121);
		add(scrollPane);

		JPanel dicePanel = new JPanel();
		dicePanel.setBounds(10, 162, 236, 214);
		add(dicePanel);
		dicePanel.setLayout(null);

		lblPlayernames = new JLabel("Noone is currently rolling");
		lblPlayernames.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPlayernames.setBounds(10, 11, 136, 14);
		dicePanel.add(lblPlayernames);

		lblrolledValue = new JLabel("00");	
		lblrolledValue.setBounds(60, 55, 124, 120);	
		dicePanel.add(lblrolledValue);
		lblrolledValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblrolledValue.setFont(new Font("Tahoma", Font.PLAIN, 99));

		lblDices = new JLabel("Nothing");
		lblDices.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDices.setBounds(153, 10, 83, 14);
		dicePanel.add(lblDices);
		
		JPanel ftpPanel = new JPanel();
		ftpPanel.setBounds(0, 387, 246, 41);
		add(ftpPanel);
		
		JButton btnStorefile = new JButton("Send file to players");
		ftpPanel.add(btnStorefile);
		
		JButton btnUpdateHP = new JButton("Update HP");
		btnUpdateHP.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (imageActive == false) {
					try {
						getElements();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					table.setEnabled(true);
					table.setVisible(true);
					imageActive = true;
					scrollPane.setEnabled(true);
					scrollPane.setVisible(true);
				} else {
					scrollPane.setEnabled(false);
					scrollPane.setVisible(false);
					table.setEnabled(false);
					table.setVisible(false);
					imageActive = false;
				}
				
			}
		});
		ftpPanel.add(btnUpdateHP);

		btnStorefile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, BMP, TIFF and PNG Images", "jpg", "tiff", "png");
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(DungeonMasterUI.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					String filename = file.getName();
					String imageType = "Null";
					
					boolean acceptedImageType = false;
					
					
					while(!acceptedImageType){
						imageType = JOptionPane.showInputDialog(null,"Image type : ","Please input your image type (Monster, location etc.)",1);
						if (!imageType.equals("")) {
							acceptedImageType = true;
						} else {
							JOptionPane.showMessageDialog(null,"Choose another image type");
						}
					}
					int healthPoints = 0;
					boolean acceptedInteger = false;
									
					//Checker om vi får tal eller bogstaver inden vi sender værdien til databasen
					while(!acceptedInteger){
						try {
							String hpString = JOptionPane.showInputDialog(null,"Health points : ","Please input amount of health points (leave blank if none)",1);
							int id = 0;
							if (hpString.equals("")) {
								acceptedInteger = true;
								try {
									ftp.storeFile(file.getPath(), filename);
									JOptionPane.showMessageDialog(null,"Your selected image was uploaded");
									
									if(firstClick == true){
										id = db.getRows("images");
										firstClick = false;
									}else {
										id = getNewestImage() + 1;
									}	
									uploadImage(id, file.getName(), imageType, healthPoints);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							} else {
								healthPoints = Integer.parseInt(hpString);
								acceptedInteger = true;
								try {
									ftp.storeFile(file.getPath(), filename);
									JOptionPane.showMessageDialog(null,"Your selected image was uploaded");
									if(firstClick == true){
										id = db.getRows("images");
										firstClick = false;
									}else {
										id = getNewestImage() + 1;
									}	
									uploadImage(id, file.getName(), imageType, healthPoints);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}	
						} catch (NumberFormatException e2) {
							JOptionPane.showMessageDialog(null,"Please only use numbers");
						}
					}	
				} else {
					System.out.println("Cancelled file choosing operation.");
				}
			}
		});

	}

	//Henter brugernavnene på de spillere der i rummet, med et limit på spillere
	public void getUser() throws Exception {
		
		int rows = db.getRows("users");
		
		String sSelectDistinct = "SELECT userName AS getUsers FROM users where userID = " + rows;
		
		try {

			db.execute(sSelectDistinct);
			ResultSet rs = db.executeQuery(sSelectDistinct);

			try {
				while (rs.next()) {	
					user = rs.getString("getUsers");
					if(rows == 1){
						btnPlayerone.setVisible(true);
						btnPlayerone.setText(user);
					}
					
					if(rows == 2){
						btnPlayertwo.setVisible(true);
						btnPlayertwo.setText(user);
					}
					
					if(rows == 3){
						btnPlayerthree.setVisible(true);
						btnPlayerthree.setText(user);
					}
					
					if(rows == 4){
						btnPlayerfour.setVisible(true);
						btnPlayerfour.setText(user);
					}
					
					if(rows == 5){
						btnPlayerfive.setVisible(true);
						btnPlayerfive.setText(user);
					}
					
					if(rows == 6){
						btnPlayersix.setVisible(true);
						btnPlayersix.setText(user);
					}
					
					
					System.out.println("User: " +user);
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
	
	
	//Setter den aktive bruger i DM brugerfladen
	public void setActiveUser(String user, int state) throws Exception {

		if (state == 1) {
			String sUpdateActive = "UPDATE users SET isTurn = " + state + " WHERE userName = '" + user + "'";
			db.execute(sUpdateActive);
			lblPlayernames.setText("Player " + user + " has rolled: ");
			playersRolling = 1;
			current = user;
		}

		if (state == 0) {
			if (playersRolling == 1) {
				if (current.equals(user)) {
					lblPlayernames.setText("Noone is currently rolling");
					String sUpdateActive = "UPDATE users SET isTurn = " + state + " WHERE userName = '" + user + "'";
					db.execute(sUpdateActive);
					playersRolling = 0;
					current = "None";
				}
			}
		}

	}
	
	//Henter hvilke terninger der blev kastet fra spilleren DM gav lov til at rulle
	public String getRolledDices() throws Exception {
		String RolledDices = "Null";
		
		int amountRows = db.getRows("dices");
		
		String sGetRolledDices = "SELECT rolledDices AS getRolledDices from dices where kastID = " + amountRows;
		
		
		try {

			db.execute(sGetRolledDices);
			ResultSet rs = db.executeQuery(sGetRolledDices);

			try {
				while (rs.next()) {					
					String sResult = rs.getString("getRolledDices");
					RolledDices = sResult;
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
		
		return RolledDices;
	}
	
	public void feedValue() throws Exception {

		int i = db.updateUserInterface(); // Henter den nyeste værdi der er blevet rullet
		lblrolledValue.setText(Integer.toString(i));
		
		lblDices.setText(getRolledDices());
		
		System.out.println("Players Rolling " +playersRolling);
	}
	
	
	public void uploadImage(int id, String imageName, String imageType, int HP) throws Exception {
		String sMakeInsert = "INSERT INTO images VALUES(" + id + "," + "'" + imageName + "'" + "," + "'" + imageType + "'" + "," + HP +")";

		db.execute(sMakeInsert); //Upload et billede til FTP server med en række argumenter
	}
	
	//Henter data fra en database table og fylder det ind i et JTable
	public void getElements() throws Exception {
		int rows = getNewestImage() + 1;
		
		while (model.getRowCount() < rows){
			String sGetElements = "SELECT imageName AS getGames from images where imageID = " +updateHPRows;
			
			
			try {

				db.execute(sGetElements);
				ResultSet rs = db.executeQuery(sGetElements);

				try {
					while (rs.next()) {
						String getGames = (rs.getString("getGames"));	
						if(model.getRowCount() < rows ){
							model.addRow(new Object[]{getGames});
						}
							
						getHealthPoints();
						
						
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
	
	//Henter healthpoints fra database og fylder det ind i en tabel
	public void getHealthPoints() throws Exception {
		int rows = model.getRowCount();
		
		for(int i = 0; i < rows; i++){
			String sGetHP = "SELECT hitPoints AS getHP from images where imageID = " +i;
				
			try {
				db.execute(sGetHP);
				ResultSet rs = db.executeQuery(sGetHP);
				try {
					while (rs.next()) {
						String getHP = rs.getString("getHP");
						model.setValueAt(getHP, i, 1);
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
	
	//Setter navnet på spillet lokalt for DM (kaldet i lobby.java)
	public void setGamename (String s) {
		lblGamename.setText(s);
	}
	
	//Laver database forbindelserne der skal til for at kunne modtage spillernes værdier osv.
	public void databaseConnection() throws Exception { 

		String sDropTable = "DROP TABLE IF EXISTS dices";
		String sDropUsers = "DROP TABLE IF EXISTS users";
		String sMakeTable = "CREATE TABLE if NOT EXISTS dices (kastID INT IDENTITY PRIMARY KEY, userName text, diceValue numeric, rolledDices text)";
		String sMakeUsers = "CREATE TABLE if NOT EXISTS users (userID INT PRIMARY KEY, userName text, isTurn boolean)";
		String sMakeImages = "CREATE TABLE if NOT EXISTS images (imageID INT PRIMARY KEY, imageName text, imageType text, hitPoints numeric)";
		String sInsert = "INSERT INTO dices VALUES (0, 'User', 0, 'DiceRolls')";

		try {

			db.execute(sDropTable);
			db.execute(sMakeTable);
			db.execute(sInsert);
			db.execute(sDropUsers);
			db.execute(sMakeUsers);
			db.execute(sMakeImages);

		} finally {
			try {
			} catch (Exception ignore) {
			}
		}
	}
	
	//Henter imageID på det nyeste billede i databasen
	public int getNewestImage() throws Exception {
		int imageID = 0;
		
		String sMakeUpdate = "SELECT imageID AS newestValue FROM images WHERE imageID = (SELECT MAX(imageID)  FROM images)";

		try {
			db.execute(sMakeUpdate);

			ResultSet rs = db.executeQuery(sMakeUpdate);

			try {
				while (rs.next()) {
					imageID = rs.getInt("newestValue");

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
		
		return imageID;
	}
}
