package otg;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.SwingConstants;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class DungeonMasterUI extends JPanel {
	int playersRolling = 0;
	
	String sDriver = "jdbc:sqlite:diceRolls.db";
	Database db = new Database(sDriver);

	public DungeonMasterUI() throws Exception{
		ImagePanel panel = new ImagePanel(
				new ImageIcon("res/background.jpg").getImage());
		setLayout(null);

		JPanel playerPanel = new JPanel();
		playerPanel.setBounds(10, 56, 236, 95);
		add(playerPanel);

		final JToggleButton btnPlayerone = new JToggleButton("playerOne");
		btnPlayerone.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (btnPlayerone.isSelected() == true) {
					try {
						setActiveUser(getUser(0), 1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						setActiveUser(getUser(0), 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});			

		btnPlayerone.setText(getUser(0));
		if(getUser(0) == "Null"){
			btnPlayerone.setVisible(false);
		}
		playerPanel.add(btnPlayerone);

		final JToggleButton btnPlayertwo = new JToggleButton("playerTwo");
		btnPlayertwo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (btnPlayertwo.isSelected() == true) {
					try {
						setActiveUser(getUser(1), 1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						setActiveUser(getUser(1), 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});
		btnPlayertwo.setText(getUser(1));
		if(getUser(1) == "Null"){
			btnPlayertwo.setVisible(false);
		}
		playerPanel.add(btnPlayertwo);

		final JToggleButton btnPlayerthree = new JToggleButton("playerThree");
		btnPlayerthree.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (btnPlayerthree.isSelected() == true) {
					try {
						setActiveUser(getUser(2), 1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						setActiveUser(getUser(2), 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});			
		btnPlayerthree.setText(getUser(2));
		if(getUser(2) == "Null"){
			btnPlayerthree.setVisible(false);
		}
		playerPanel.add(btnPlayerthree);

		final JToggleButton btnPlayerfour = new JToggleButton("playerFour");
		btnPlayerfour.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (btnPlayerfour.isSelected() == true) {
					try {
						setActiveUser(getUser(3), 1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						setActiveUser(getUser(3), 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});			
		btnPlayerfour.setText(getUser(3));
		if(getUser(3) == "Null"){
			btnPlayerfour.setVisible(false);
		}
		playerPanel.add(btnPlayerfour);

		final JToggleButton btnPlayerfive = new JToggleButton("playerFive");
		btnPlayerfive.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (btnPlayerfive.isSelected() == true) {
					try {
						setActiveUser(getUser(4), 1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						setActiveUser(getUser(4), 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});			
		btnPlayerfive.setText(getUser(4));
		if(getUser(4) == "Null"){
			btnPlayerfive.setVisible(false);
		}
		playerPanel.add(btnPlayerfive);

		final JToggleButton btnPlayersix = new JToggleButton("playerSix");
		btnPlayersix.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (btnPlayersix.isSelected() == true) {
					try {
						setActiveUser(getUser(5), 1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						setActiveUser(getUser(5), 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});			
		btnPlayersix.setText(getUser(5));
		if(getUser(5) == "Null"){
			btnPlayersix.setVisible(false);
		}
		playerPanel.add(btnPlayersix);

		JPanel dicePanel = new JPanel();
		dicePanel.setBounds(10, 162, 236, 284);
		add(dicePanel);
		dicePanel.setLayout(null);

		JLabel lblPlayernames = new JLabel("Player One has rolled: ");
		lblPlayernames.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlayernames.setBounds(10, 11, 136, 14);
		dicePanel.add(lblPlayernames);

		JLabel lblrolledValue = new JLabel("0");
		lblrolledValue.setBounds(92, 79, 54, 120);
		lblrolledValue.setFont(new Font("Tahoma", Font.PLAIN, 99));
		dicePanel.add(lblrolledValue);

		JLabel lblDices = new JLabel("D20, D4");
		lblDices.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDices.setBounds(143, 11, 83, 14);
		dicePanel.add(lblDices);

		JLabel lblGamename = new JLabel("Attack of the Swedes");
		lblGamename.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamename.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGamename.setBounds(10, 11, 236, 14);
		add(lblGamename);

	}

	public String getUser(int i) throws Exception {
		String user = "Null";
		String sSelectDistinct = "SELECT DISTINCT userName AS getUsers FROM users where userID = " + i;
		
		try {

			db.execute(sSelectDistinct);
			ResultSet rs = db.executeQuery(sSelectDistinct);

			try {
				while (rs.next()) {					
					String sResult = rs.getString("getUsers");
					user = sResult;
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
		return user;
	}
	
	
	public void setActiveUser(String user, int state) throws Exception {

		String sUpdateActive = "UPDATE users SET isTurn = " + state + " WHERE userName = '" + user + "'";

		db.execute(sUpdateActive);

	}
}
