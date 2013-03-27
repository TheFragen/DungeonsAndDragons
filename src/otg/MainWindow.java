package otg;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
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

public class MainWindow {

	private JFrame frame;
	private JTextPane txtRollingPane;
	boolean RollingEmpty = false;
	private JLabel lblResult;

	int randomInt = 0;
	int rolledValue;
	String user = "Null";
	int diceRolling = 0;

	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});   t.start();

		Thread tableThread = new Thread(new Runnable() {
			public void run() {
				try {
					for (int i = Integer.MAX_VALUE; i > 0; i--) {
						updateUserInterface();
						Thread.sleep(2000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});  tableThread.start();
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public MainWindow() throws Exception {
		databaseConnection();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Initialiser vinduet og tilføj baggrundsbillede
		ImagePanel panel = new ImagePanel(
				new ImageIcon("res/background.jpg").getImage());
		frame = new JFrame("D&D Dice Roller");
		frame.setResizable(false);
		frame.setBounds(100, 100, 256, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel KnapPanel = new JPanel();
		KnapPanel.setBounds(0, 0, 88, 419);
		frame.getContentPane().add(KnapPanel);

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
		lblRoll.setBounds(117, 90, 88, 29);
		frame.getContentPane().add(lblRoll);

		JPanel rollpanel = new JPanel();
		rollpanel.setBounds(105, 125, 112, 169);
		frame.getContentPane().add(rollpanel);
		rollpanel.setLayout(null);

		rollpanel.add(lblRolling);

		rollpanel.add(txtRollingPane);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 80));
		lblResult.setBounds(13, 28, 89, 116);

		rollpanel.add(lblResult);

		// Knap der rydder alle tidligere brugerændringer på nær brugernavn
		JButton btnClear = new JButton("Clear");
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
		btnClear.setBounds(117, 324, 89, 23);
		frame.getContentPane().add(btnClear);

		// Knap der ruller terningerne og sender det til en database
		JButton btnRollDice = new JButton("Roll Dice");
		btnRollDice.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (user == "Null") {
					System.out.println("Please enter a username");
				} else {
					if (diceRolling == 0) {
						System.out.println("Please select a dice first");
					} else {
						System.out.println("Dices rolled, value: "
								+ rolledValue);
						lblResult.setText(Integer.toString(rolledValue));
						int id = 0;
						int diceVal = rolledValue;

						try {
							updateTable(id, user, diceVal);
							System.out
									.println("Database updated with new value");
						} catch (Exception e1) {
							e1.printStackTrace();
						}

						id++;
					}
				}
			}
		});
		btnRollDice.setBounds(117, 298, 89, 23);
		frame.getContentPane().add(btnRollDice);

		// Sæt et brugernavn
		txtUsername = new JTextField();
		txtUsername.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == e.VK_ENTER) {
					String s = txtUsername.getText();
					user = s;
					System.out.println("Username is set " + user);
				}
			}
		});
		txtUsername.setText("Username");

		txtUsername.setBounds(117, 21, 86, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		JTextPane txtpnDatabaseValue = new JTextPane();
		txtpnDatabaseValue.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtpnDatabaseValue.setText("database Value");
		txtpnDatabaseValue.setBounds(98, 357, 126, 61);
		frame.getContentPane().add(txtpnDatabaseValue);

		frame.getContentPane().add(panel);

	}

	private void databaseConnection() throws Exception {

		String sDropTable = "DROP TABLE IF EXISTS dices";
		String sMakeTable = "CREATE TABLE dices (kastID numeric, userName text, diceValue numeric)";
		String sMakeSelect = "SELECT diceValue from dices";

		String sDriver = "jdbc:sqlite";
		String sDatabaseToUse = "diceRolls.db";

		String sDbUrl = sDriver + ":" + sDatabaseToUse;

		Database db = new Database(sDbUrl);
		try {

			db.execute(sDropTable);
			db.execute(sMakeTable);

			ResultSet rs = db.executeQuery(sMakeSelect);
			try {
				while (rs.next()) {
					String sResult = rs.getString("diceValue");
					int i = Integer.valueOf(sResult);
					System.out.println("Value in Database: " + i);
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

	public void updateTable(int id, String userName, int dice) throws Exception {
		String sMakeInsert = "INSERT INTO dices VALUES(" + id + "," + "'"
				+ userName + "'" + "," + dice + ")";

		String sDriver = "jdbc:sqlite";
		String sDatabaseToUse = "diceRolls.db";
		String sDbUrl = sDriver + ":" + sDatabaseToUse;
		Database db = new Database(sDbUrl);
		db.execute(sMakeInsert);
	}

	public static void updateUserInterface() throws Exception {
		String sDriver = "jdbc:sqlite";
		String sDatabaseToUse = "diceRolls.db";
		String sDbUrl = sDriver + ":" + sDatabaseToUse;
		Database db = new Database(sDbUrl);

		String sMakeUpdate = "SELECT COUNT(*) FROM dices";

		
		try {
			db.execute(sMakeUpdate);

			ResultSet rs = db.executeQuery(sMakeUpdate);
			try {
				while (rs.next()) {
					int sResult = rs.getRow();
					System.out.println("Number of rows " + sResult);
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
