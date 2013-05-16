package otg;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class MainWindow {

	public JFrame frame;
	JPanel cards;

	DungeonMasterUI dm;
	PlayerUI player;

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
		});
		t.start();

	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 * @wbp.parser.entryPoint
	 */
	public MainWindow() throws Exception {
		cards = new JPanel(new CardLayout());
		CardSetup();

	}

	public void CardSetup() throws Exception {
		frame = new JFrame("D&D Dice Roller");
		frame.setResizable(false);
		frame.setBounds(100, 100, 256, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Lobby lobby = new Lobby(this);
		cards.add(lobby);
		frame.add(cards);	
	}

	public void dungeonmasterCard(DungeonMasterUI dm) throws Exception {
		this.dm = dm;		
		cards.add(dm);	
		((CardLayout)cards.getLayout()).next(cards);		
	}
	
	public void playerCard(PlayerUI player) throws Exception {
		this.player = player;
		cards.add(player);	
		((CardLayout)cards.getLayout()).last(cards);		
	}
	
	public void backToLobby() throws Exception {
		((CardLayout)cards.getLayout()).first(cards);		
	}
}
