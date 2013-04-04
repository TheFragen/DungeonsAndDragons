package otg;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class DungeonMaster extends JFrame {

	private JPanel contentPane;

	DungeonMaster frame = new DungeonMaster();

	public DungeonMaster() {
		DungeonMasterInitialize();

	}

	public void DungeonMasterInitialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 256, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel playerPanel = new JPanel();
		playerPanel.setBounds(10, 11, 220, 92);
		contentPane.add(playerPanel);

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

		JPanel rolledPanel = new JPanel();
		rolledPanel.setBounds(10, 114, 220, 294);
		contentPane.add(rolledPanel);
		rolledPanel.setLayout(null);

		JLabel lblPlayerRolled = new JLabel("0");
		lblPlayerRolled.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblPlayerRolled.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerRolled.setBounds(10, 63, 200, 220);
		rolledPanel.add(lblPlayerRolled);

		JLabel lblplayerNames = new JLabel("Christians has rolled:");
		lblplayerNames.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblplayerNames.setBounds(10, 11, 132, 17);
		rolledPanel.add(lblplayerNames);

		JLabel lblDices = new JLabel("D20, D4");
		lblDices.setBounds(164, 11, 46, 14);
		rolledPanel.add(lblDices);
	}
}
