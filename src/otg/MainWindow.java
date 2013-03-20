package otg;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ImagePanel panel = new ImagePanel(new ImageIcon("res/background.jpg").getImage());
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JLabel lblD = new JLabel("d4");
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		lblD.setForeground(Color.WHITE);
		lblD.setBounds(20, 20, 46, 14);
		
		JLabel lb2D = new JLabel("d6");
		lb2D.setHorizontalAlignment(SwingConstants.CENTER);
		lb2D.setForeground(Color.WHITE);
		lb2D.setBounds(20, 80, 46, 14);
		
		JLabel lb3D = new JLabel("d8");
		lb3D.setHorizontalAlignment(SwingConstants.CENTER);
		lb3D.setForeground(Color.WHITE);
		lb3D.setBounds(20, 140, 46, 14);
		
		JLabel lb4D = new JLabel("d10");
		lb4D.setHorizontalAlignment(SwingConstants.CENTER);
		lb4D.setForeground(Color.WHITE);
		lb4D.setBounds(20, 200, 46, 14);
		
		JLabel lb5D = new JLabel("d12");
		lb5D.setHorizontalAlignment(SwingConstants.CENTER);
		lb5D.setForeground(Color.WHITE);
		lb5D.setBounds(20, 260, 46, 14);
		
		JLabel lb6D = new JLabel("d20");
		lb6D.setHorizontalAlignment(SwingConstants.CENTER);
		lb6D.setForeground(Color.WHITE);
		lb6D.setBounds(20, 320, 46, 14);
		
		JLabel lb7D = new JLabel("d100");
		lb7D.setHorizontalAlignment(SwingConstants.CENTER);
		lb7D.setForeground(Color.WHITE);
		lb7D.setBounds(20, 380, 46, 14);
		
		
		frame.getContentPane().add(lblD);
		frame.getContentPane().add(lb2D);
		frame.getContentPane().add(lb3D);
		frame.getContentPane().add(lb4D);
		frame.getContentPane().add(lb5D);
		frame.getContentPane().add(lb6D);
		frame.getContentPane().add(lb7D);
		
		
		JLabel label1 = new JLabel();  
		label1.setIcon(new ImageIcon("res/blacksquare.jpg"));
		label1.setBounds(20, 5, 50, 50);
		frame.getContentPane().add(label1); 
		
		JLabel label2 = new JLabel();  
		label2.setIcon(new ImageIcon("res/blacksquare.jpg"));
		label2.setBounds(20, 65, 50, 50);
		frame.getContentPane().add(label2);
		
		JLabel label3 = new JLabel();  
		label3.setIcon(new ImageIcon("res/blacksquare.jpg"));
		label3.setBounds(20, 125, 50, 50);
		frame.getContentPane().add(label3);
		
		JLabel label4 = new JLabel();  
		label4.setIcon(new ImageIcon("res/blacksquare.jpg"));
		label4.setBounds(20, 185, 50, 50);
		frame.getContentPane().add(label4);
		
		JLabel label5 = new JLabel();  
		label5.setIcon(new ImageIcon("res/blacksquare.jpg"));
		label5.setBounds(20, 245, 50, 50);
		frame.getContentPane().add(label5);
		
		JLabel label6 = new JLabel();  
		label6.setIcon(new ImageIcon("res/blacksquare.jpg"));
		label6.setBounds(20, 305, 50, 50);
		frame.getContentPane().add(label6);
		
		JLabel label7 = new JLabel();  
		label7.setIcon(new ImageIcon("res/blacksquare.jpg"));
		label7.setBounds(20, 365, 50, 50);
		frame.getContentPane().add(label7);
		
		
		frame.getContentPane().add(panel);
		frame.setBounds(100, 100, 256, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
