package otg;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class DungeonMasterUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public DungeonMasterUI() {
		ImagePanel panel = new ImagePanel(
				new ImageIcon("res/background.jpg").getImage());
		setLayout(null);
		
		JPanel playerPanel = new JPanel();
		playerPanel.setBounds(10, 56, 236, 95);
		add(playerPanel);
		
		JButton btnPlayerone = new JButton("playerOne");
		playerPanel.add(btnPlayerone);
		
		JButton btnPlayertwo = new JButton("playerTwo");
		playerPanel.add(btnPlayertwo);
		
		JButton btnPlayerthree = new JButton("playerThree");
		playerPanel.add(btnPlayerthree);
		
		JButton btnPlayerfour = new JButton("playerFour");
		playerPanel.add(btnPlayerfour);
		
		JButton btnPlayerfive = new JButton("playerFive");
		playerPanel.add(btnPlayerfive);
		
		JButton btnPlayersix = new JButton("playerSix");
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
}
