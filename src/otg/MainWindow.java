package otg;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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
		CardsTest();

	}

	public void CardsTest() throws Exception {
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
}
