package otg;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.sql.*;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class PlayerUI extends JPanel {

	private static final long serialVersionUID = 7355976855868497491L;

	FTP ftp = new FTP();	

	private JLabel ftpImage;
	private JPanel imagePanel;
	private JLabel ftpImageType;
	private JButton btnClear;
	private JButton btnRollDice;
	private JLabel lblHealthpoints;
	private Thread tableThread;

	private JTextPane txtRollingPane;
	boolean RollingEmpty = false;
	private JLabel lblResult;
	private JLabel lblDatabasevalue;
	private JLabel lblActiveplayer;
	private JLabel lblFtpNotification;
	boolean imageActive = false;
	boolean cunt = true;;
	static int rows = 0;
	JPanel cards;
	
	Database db;
	static int valueInRow = 0;

	int randomInt = 0;
	int rolledValue;
	String user = "Null";
	int diceRolling = 0;
	static boolean firstRun = true;

	private JTextField txtUsername;

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 * @wbp.parser.entryPoint
	 */
	public PlayerUI(Database db) throws Exception {
		this.db = db;
		initialize();

		tableThread = new Thread(new Runnable() {
			public void run() {

				for (int i = 0; i == 0; i = 0) {
					try {
						setActiveUser();
						System.out.println("Current active user is: " +getActiveUser());
						setNewNotification();
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
		});tableThread.start();
	}

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 * 
	 * @throws Exception
	 */
	public void initialize() {

		// Initialiser vinduet og tilføj baggrundsbillede
		ImagePanel panel = new ImagePanel(
				new ImageIcon("res/background.jpg").getImage());
		setLayout(null);

		lblFtpNotification = new JLabel("ftpImage");
		lblFtpNotification.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (imageActive == false) {
					imagePanel.setBounds(0, 0, 250, 429);
					txtUsername.setEnabled(false);
					txtUsername.setVisible(false);
					txtRollingPane.setVisible(false);
					ftpImage.setEnabled(true);
					ftpImage.setVisible(true);	
					btnClear.setEnabled(false);
					btnClear.setVisible(false);
					btnRollDice.setEnabled(false);
					btnRollDice.setVisible(false);
					imageActive = true;

				} else {
					imagePanel.setBounds(0, 0, -1, -1);
					txtRollingPane.setVisible(true);
					txtUsername.setEnabled(true);
					txtUsername.setVisible(true);
					ftpImage.setEnabled(true);
					ftpImage.setVisible(true);	
					btnClear.setEnabled(true);
					btnClear.setVisible(true);
					btnRollDice.setEnabled(true);
					btnRollDice.setVisible(true);
					lblFtpNotification.setIcon(new ImageIcon("res/notificationNone.png"));
					ftpImage.setEnabled(false);
					ftpImage.setVisible(false);
					imageActive = false;
				}
			}
		});
		lblFtpNotification.setBounds(220, 5, 25, 25);
		lblFtpNotification.setIcon(new ImageIcon("res/notificationNone.png"));
		add(lblFtpNotification);


		imagePanel = new JPanel();
		imagePanel.setBackground(Color.WHITE);
		imagePanel.setBounds(0, 0, -1, -1);
		add(imagePanel);
		imagePanel.setLayout(null);

		ftpImageType = new JLabel("New label");
		ftpImageType.setForeground(Color.BLACK);
		ftpImageType.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ftpImageType.setBounds(5, 375, 246, 43);
		imagePanel.add(ftpImageType);

		JLabel lblftpImageType = new JLabel("Image type: ");
		lblftpImageType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblftpImageType.setForeground(Color.BLACK);
		lblftpImageType.setBounds(5, 355, 87, 29);
		imagePanel.add(lblftpImageType);
		lblftpImageType.setBackground(Color.BLACK);

		ftpImage = new JLabel("ftpImage");
		ftpImage.setBounds(0, 0, 251, 429);
		imagePanel.add(ftpImage);
		ftpImage.setBackground(Color.BLACK);
		ftpImage.setEnabled(false);

		lblHealthpoints = new JLabel("Healthpoints");
		lblHealthpoints.setBounds(5, 5, 92, 29);
		imagePanel.add(lblHealthpoints);
		ftpImage.setVisible(false);


		JPanel KnapPanel = new JPanel();
		KnapPanel.setBounds(0, 0, 88, 419);
		add(KnapPanel);

		txtRollingPane = new JTextPane();
		txtRollingPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtRollingPane.setBackground(new Color(240, 240, 240));
		txtRollingPane.setEditable(false);
		txtRollingPane.setText("Nothing");
		txtRollingPane.setBounds(47, 0, 65, 38);

		JLabel lblRolling = new JLabel("Rolling: ");
		lblRolling.setBounds(3, 3, 46, 14);

		lblResult = new JLabel(Integer.toString(randomInt));

		// Tilføj billeder
		JLabel label1 = new JLabel();
		label1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (RollingEmpty == true) {
					String existingText = txtRollingPane.getText();
					txtRollingPane.setText(existingText + " , D4");
					randomInt = (int) (Math.random() * 4 + 1);
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				} else {
					txtRollingPane.setText("D4");
					RollingEmpty = true;
					randomInt = (int) (Math.random() * 4 + 1);
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				}

			}
		});
		label1.setIcon(new ImageIcon("res/blacksquared4.jpg"));
		KnapPanel.add(label1);

		// Kode for D6 terningen
		JLabel label2 = new JLabel();
		label2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (RollingEmpty == true) {
					String existingText = txtRollingPane.getText();
					txtRollingPane.setText(existingText + " , D6");
					randomInt = (int) (Math.random() * 6 + 1);
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				} else {
					txtRollingPane.setText("D6");
					RollingEmpty = true;
					randomInt = (int) (Math.random() * 6 + 1);
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				}
			}
		});
		KnapPanel.add(label2);
		label2.setIcon(new ImageIcon("res/blacksquared6.jpg"));

		// Kode for D8 terningen
		JLabel label3 = new JLabel();
		label3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (RollingEmpty == true) {
					String existingText = txtRollingPane.getText();
					txtRollingPane.setText(existingText + " , D8");
					randomInt = (int) (Math.random() * 8 + 1);
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				} else {
					txtRollingPane.setText("D8");
					RollingEmpty = true;
					randomInt = (int) (Math.random() * 8 + 1);
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				}
			}
		});
		KnapPanel.add(label3);
		label3.setIcon(new ImageIcon("res/blacksquared8.jpg"));

		// Kode for D10 terningen
		JLabel label4 = new JLabel();
		label4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (RollingEmpty == true) {
					String existingText = txtRollingPane.getText();
					txtRollingPane.setText(existingText + " , D10");
					randomInt = (int) (Math.random() * 10 + 1);
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				} else {
					txtRollingPane.setText("D10");
					RollingEmpty = true;
					randomInt = (int) (Math.random() * 10 + 1);
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				}
			}
		});
		KnapPanel.add(label4);
		label4.setIcon(new ImageIcon("res/blacksquared10.jpg"));

		// Kode for D12 terningen
		JLabel label5 = new JLabel();
		label5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (RollingEmpty == true) {
					String existingText = txtRollingPane.getText();
					txtRollingPane.setText(existingText + " , D12");
					randomInt = (int) (Math.random() * 12 + 1);
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				} else {
					txtRollingPane.setText("D12");
					RollingEmpty = true;
					randomInt = (int) (Math.random() * 12 + 1);
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				}
			}
		});
		KnapPanel.add(label5);
		label5.setIcon(new ImageIcon("res/blacksquared12.jpg"));

		// Kode for D20 terningen
		JLabel label6 = new JLabel();
		label6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (RollingEmpty == true) {
					String existingText = txtRollingPane.getText();
					txtRollingPane.setText(existingText + " , D20");
					int range = 20;
					randomInt = (int) (Math.random() * range / 2) * 2;
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				} else {
					txtRollingPane.setText("D20");
					RollingEmpty = true;
					int range = 20;
					randomInt = (int) (Math.random() * range / 2) * 2;
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				}
			}
		});
		KnapPanel.add(label6);
		label6.setIcon(new ImageIcon("res/blacksquared20.jpg"));

		// Kode for D100 terningen
		JLabel label7 = new JLabel();
		label7.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (RollingEmpty == true) {
					String existingText = txtRollingPane.getText();
					txtRollingPane.setText(existingText + " , D100");
					randomInt = (int) (Math.random() * 100 + 1);
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				} else {
					txtRollingPane.setText("D100");
					RollingEmpty = true;
					randomInt = (int) (Math.random() * 100 + 1);
					int i = rolledValue + randomInt;
					rolledValue = i;
					diceRolling++;
				}
			}
		});
		KnapPanel.add(label7);
		label7.setIcon(new ImageIcon("res/blacksquared100.jpg"));

		// Overskrift til rollPanel
		JLabel lblRoll = new JLabel("Roll!");
		lblRoll.setFont(new Font("Verdana", Font.BOLD, 23));
		lblRoll.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoll.setBounds(118, 65, 88, 29);
		add(lblRoll);

		JPanel rollpanel = new JPanel();
		rollpanel.setBounds(106, 100, 112, 169);
		add(rollpanel);
		rollpanel.setLayout(null);

		rollpanel.add(lblRolling);

		rollpanel.add(txtRollingPane);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 80));
		lblResult.setBounds(13, 28, 89, 116);

		rollpanel.add(lblResult);

		// Knap der rydder alle tidligere brugerændringer på nær brugernavn
		btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				diceRolling = 0;
				lblResult.setText("0");
				txtRollingPane.setText("Nothing");
				RollingEmpty = false;
				rolledValue = 0;
				System.out.println("Cleared");
			}
		});
		btnClear.setBounds(118, 306, 89, 23);
		add(btnClear);

		// Knap der ruller terningerne og sender det til en database
		btnRollDice = new JButton("Roll Dice");
		btnRollDice.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (user == "Null") {
					JOptionPane.showMessageDialog(null, "Please enter a username");
				} else {
					if (diceRolling == 0) {
						JOptionPane.showMessageDialog(null, "Please select a dice first");
					} else {					
						try {
							if (isPlayerTurn(user) == true) {
								System.out.println("Dices rolled, value: " + rolledValue);
								lblResult.setText(Integer.toString(rolledValue));
								int id = 0;
								try {
									id = db.getRows("dices") + 1;
								} catch (Exception e2) {
									e2.printStackTrace();
								}
								int diceVal = rolledValue;

								try {
									updateTable(id, user, diceVal,
											txtRollingPane.getText());
									System.out.println("Database updated with new value");
								} catch (Exception e1) {
									e1.printStackTrace();

								}
							} else {
								diceRolling = 0;
								lblResult.setText("0");
								txtRollingPane.setText("Nothing");
								RollingEmpty = false;
								rolledValue = 0;
								System.out.println("Cleared");
							}
						} catch (Exception e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}

					}
				}
			}
		});
		btnRollDice.setBounds(118, 280, 89, 23);
		add(btnRollDice);

		// Sæt et brugernavn
		txtUsername = new JTextField();
		txtUsername.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_ENTER) {
					String s = txtUsername.getText();
					user = s;
					try {
						setUsername(db.getRows("users"), user);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					System.out.println("Username is set " + user);
				}
			}
		});
		txtUsername.setText("Username");

		txtUsername.setBounds(117, 21, 86, 20);
		add(txtUsername);
		txtUsername.setColumns(10);

		JPanel databaseValue = new JPanel();
		databaseValue.setBounds(98, 340, 142, 78);
		add(databaseValue);
		databaseValue.setLayout(null);

		JLabel lblPlayerCurrentlyRolling = new JLabel("Player currently rolling:");
		lblPlayerCurrentlyRolling.setBounds(0, 5, 142, 14);
		databaseValue.add(lblPlayerCurrentlyRolling);
		lblPlayerCurrentlyRolling.setHorizontalAlignment(SwingConstants.CENTER);

		lblActiveplayer = new JLabel("None");
		lblActiveplayer.setBounds(0, 20, 142, 14);
		databaseValue.add(lblActiveplayer);
		lblActiveplayer.setHorizontalAlignment(SwingConstants.CENTER);

		// Lav label der henter værdi fra database

		lblDatabasevalue = new JLabel("Null");
		lblDatabasevalue.setBounds(46, 39, 53, 39);
		databaseValue.add(lblDatabasevalue);
		lblDatabasevalue.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabasevalue.setFont(new Font("Tahoma", Font.PLAIN, 32));

	}


	public void updateTable(int id, String userName, int dice, String rolledDices) throws Exception {
		String sMakeInsert = "INSERT INTO dices VALUES(" + id + "," + "'" + userName + "'" + "," + dice + "," + "'" + rolledDices + "'" +")";

		db.execute(sMakeInsert);
	}


	public void setUsername(int id, String userName) throws Exception {
		int id2 = id + 1;
		String sMakeInsert = "INSERT INTO users VALUES(" + id2 + "," + "'" + userName + "'" + "," + 0 + ")";

		ResultSet rs = db.executeQuery("SELECT userName FROM users WHERE userName LIKE '" + userName + "'");
		if (rs.next()) {
			JOptionPane.showMessageDialog(null, "Please select another name.");
		} else {
			db.execute(sMakeInsert);
		}
		try {
			rs.close();
		} catch (Exception ignore) {
		}
	}


	public boolean isPlayerTurn(String user) throws Exception {
		String sGetTurn = "SELECT isTurn AS getTurn from users where userName = '" + user + "'";
		boolean turn = false;

		try {
			db.execute(sGetTurn);
			ResultSet rs = db.executeQuery(sGetTurn);

			try {
				while (rs.next()) {
					int getTurn = rs.getInt("getTurn");
					if(getTurn == 0){
						turn = false;
						JOptionPane.showMessageDialog(null, "It is not your turn.");
					}
					if(getTurn == 1){
						turn = true;
					}

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
		return turn;
	}

	public void setActiveUser() throws Exception {
		String s = getActiveUser();
		lblActiveplayer.setText(s);

		lblDatabasevalue.setText(Integer.toString(db.updateUserInterface()));
	}

	public void setNewNotification() throws Exception {
		int i = db.getRows("images") - 1;

		String sSetNewNotification = "SELECT imageName AS getImage from images where imageID = " + i ;

		try {
			db.execute(sSetNewNotification);
			ResultSet rs = db.executeQuery(sSetNewNotification);

			try {
				while (rs.next()) {
					String getImage = rs.getString("getImage");
					System.out.println("notifications/" +getImage);
					File file = new File("notifications/" + getImage);
					if (file.isFile() == true) {
						System.out.println("File already exists");
						ftpImageType.setText(getImageType());
						lblHealthpoints.setText("Health: " +getHealthPoints(i));
					}
					if (file.isFile() == false || firstRun == true) {
						System.out.println("New file downloaded");
						ftp.getFile(getImage);
						String imageToShow = "Null";

						// Skalerer billedet fra FTP serveren
						File file2 = new File("notifications/" + getImage);
						BufferedImage sourceImage = ImageIO.read(file2);
						if (sourceImage.getWidth() > 250 || sourceImage.getHeight() > 429) {
							Image scaledImage = sourceImage.getScaledInstance(250, -1, Image.SCALE_SMOOTH);
							BufferedImage bufferedScaledImage = new BufferedImage(
									scaledImage.getWidth(null),
									scaledImage.getHeight(null),
									BufferedImage.TYPE_INT_RGB);
							bufferedScaledImage.getGraphics().drawImage(scaledImage, 0, 0, null);
							File outputfile = new File("notifications/scaled-" + getImage);
							ImageIO.write(bufferedScaledImage, "jpeg", outputfile);
							lblFtpNotification.setIcon(new ImageIcon("res/notificationNew.png"));
							imageToShow = "notifications/scaled-" + getImage;

						} else {
							lblFtpNotification.setIcon(new ImageIcon("res/notificationNew.png"));
							imageToShow = "notifications/" + getImage;
						}

						ftpImage.setIcon(new ImageIcon(imageToShow));
						ftpImageType.setText(getImageType());
						lblHealthpoints.setText("Health: " +getHealthPoints(i));
						firstRun = false;

					}
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

	public String getImageType() throws Exception {
		String imageType = null;

		int i = db.getRows("images") - 1;

		String sGetImageType = "SELECT imageType AS getImageType from images where imageID = " + i;

		try {
			db.execute(sGetImageType);
			ResultSet rs = db.executeQuery(sGetImageType);

			try {
				while (rs.next()) {
					String getTurn = rs.getString("getImageType");
					imageType = getTurn;

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

		return imageType;

	}

	public String getHealthPoints(int i) throws Exception {
		String HP = "Null";

		String sGetHealthPoints = "SELECT hitPoints AS getHP from images where imageID = " + i;

		try {
			db.execute(sGetHealthPoints);
			ResultSet rs = db.executeQuery(sGetHealthPoints);

			try {
				while (rs.next()) {
					int getHP = rs.getInt("getHP");
					HP = Integer.toString(getHP);
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
		return HP;
	}
	
	public String getActiveUser() throws Exception {
		String currentActive = "No one";
		String sActivePlayer = "SELECT userName AS activeUser from users where isTurn = 1";

		try {
			db.execute(sActivePlayer);
			ResultSet rs = db.executeQuery(sActivePlayer);
			try {
				while (rs.next()) {
					String getActiveUser2 = rs.getString("activeUser");
					currentActive = getActiveUser2;
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
