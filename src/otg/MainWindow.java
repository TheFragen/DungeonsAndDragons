package otg;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JButton;

public class MainWindow {

	private JFrame frame;
	private JTextPane txtRollingPane;
	private JLabel lblIntegers;
	boolean RollingEmpty = false;
	private JLabel lblResult;
	
	int d4Pressed = 0;
	int d6Pressed = 0;
	int randomInt = 0;
	
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
			
		//Initialiser vinduet og tilføj baggrundsbillede
		ImagePanel panel = new ImagePanel(new ImageIcon("res/background.jpg").getImage());
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
		
	
		//Tilføj billeder
		JLabel label1 = new JLabel(); 
		label1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(RollingEmpty == true){
				String existingText;
				existingText = txtRollingPane.getText();
				txtRollingPane.setText(existingText +" , D4");
				d4Pressed++;
				lblIntegers.setText("N" +d4Pressed +d6Pressed);
				randomInt = (int)(Math.random() * 4+1);
				String existingValue = lblResult.getText();
				int i = Integer.valueOf(existingValue) +randomInt;
				lblResult.setText(Integer.toString(i));
				}else{
					txtRollingPane.setText("D4");
					RollingEmpty = true;
					d4Pressed++;
					lblIntegers.setText("N" +d4Pressed +d6Pressed);
					randomInt = (int)(Math.random() * 4+1);
					String existingValue = lblResult.getText();
					int i = Integer.valueOf(existingValue) +randomInt;
					lblResult.setText(Integer.toString(i));
				}
				
			}
		});
		label1.setIcon(new ImageIcon("res/blacksquared4.jpg"));
		KnapPanel.add(label1);    
		
		
		JLabel label2 = new JLabel();
		label2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(RollingEmpty == true){
					String existingText;
					existingText = txtRollingPane.getText();
					txtRollingPane.setText(existingText +" , D6");	
					d6Pressed++;
					lblIntegers.setText("N" +d4Pressed +d6Pressed);
					randomInt = (int)(Math.random() * 6+1);
					String existingValue = lblResult.getText();
					int i = Integer.valueOf(existingValue) +randomInt;
					lblResult.setText(Integer.toString(i));
					}else{				
						txtRollingPane.setText("D6");
						RollingEmpty = true;
						d6Pressed++;
						lblIntegers.setText("N" +d4Pressed +d6Pressed);
						randomInt = (int)(Math.random() * 6+1);
						String existingValue = lblResult.getText();
						int i = Integer.valueOf(existingValue) +randomInt;
						lblResult.setText(Integer.toString(i));
					}
			}
		});		
		KnapPanel.add(label2);
		label2.setIcon(new ImageIcon("res/blacksquared6.jpg"));
		
		
		JLabel label3 = new JLabel(); 
		label3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(RollingEmpty == true){
					String existingText;
					existingText = txtRollingPane.getText();
					txtRollingPane.setText(existingText +" , D8");
					randomInt = (int)(Math.random() * 8+1);
					String existingValue = lblResult.getText();
					int i = Integer.valueOf(existingValue) +randomInt;
					lblResult.setText(Integer.toString(i));
					}else{				
						txtRollingPane.setText("D8");
						RollingEmpty = true;
						randomInt = (int)(Math.random() * 8+1);
						String existingValue = lblResult.getText();
						int i = Integer.valueOf(existingValue) +randomInt;
						lblResult.setText(Integer.toString(i));
					}
			}
		});
		KnapPanel.add(label3);
		label3.setIcon(new ImageIcon("res/blacksquared8.jpg"));
		
		
		JLabel label4 = new JLabel();  
		label4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(RollingEmpty == true){
					String existingText;
					existingText = txtRollingPane.getText();
					txtRollingPane.setText(existingText +" , D10");	
					randomInt = (int)(Math.random() * 10+1);
					String existingValue = lblResult.getText();
					int i = Integer.valueOf(existingValue) +randomInt;
					lblResult.setText(Integer.toString(i));
					}else{				
						txtRollingPane.setText("D10");
						RollingEmpty = true;
						randomInt = (int)(Math.random() * 10+1);
						String existingValue = lblResult.getText();
						int i = Integer.valueOf(existingValue) +randomInt;
						lblResult.setText(Integer.toString(i));
					}
			}
		});
		KnapPanel.add(label4);
		label4.setIcon(new ImageIcon("res/blacksquared10.jpg"));
		
		
		JLabel label5 = new JLabel();
		label5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(RollingEmpty == true){
					String existingText;
					existingText = txtRollingPane.getText();
					txtRollingPane.setText(existingText +" , D12");	
					randomInt = (int)(Math.random() * 12+1);
					String existingValue = lblResult.getText();
					int i = Integer.valueOf(existingValue) +randomInt;
					lblResult.setText(Integer.toString(i));
					}else{				
						txtRollingPane.setText("D12");
						RollingEmpty = true;
						randomInt = (int)(Math.random() * 12+1);
						String existingValue = lblResult.getText();
						int i = Integer.valueOf(existingValue) +randomInt;
						lblResult.setText(Integer.toString(i));
					}
			}
		});
		KnapPanel.add(label5);
		label5.setIcon(new ImageIcon("res/blacksquared12.jpg"));
		
		
		JLabel label6 = new JLabel();  
		label6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(RollingEmpty == true){
					String existingText;
					existingText = txtRollingPane.getText();
					txtRollingPane.setText(existingText +" , D20");	
					int range = 20;
					randomInt = (int)(Math.random() * range / 2 ) * 2;
					String existingValue = lblResult.getText();
					int i = Integer.valueOf(existingValue) +randomInt;
					lblResult.setText(Integer.toString(i));
					}else{				
						txtRollingPane.setText("D20");
						RollingEmpty = true;
						int range = 20;
						randomInt = (int)(Math.random() * range / 2 ) * 2;
						String existingValue = lblResult.getText();
						int i = Integer.valueOf(existingValue) +randomInt;
						lblResult.setText(Integer.toString(i));
					}
			}
		});
		KnapPanel.add(label6);
		label6.setIcon(new ImageIcon("res/blacksquared20.jpg"));
		
		
		JLabel label7 = new JLabel();  
		label7.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(RollingEmpty == true){
					String existingText;
					existingText = txtRollingPane.getText();
					txtRollingPane.setText(existingText +" , D100");	
					randomInt = (int)(Math.random() * 100+1);
					String existingValue = lblResult.getText();
					int i = Integer.valueOf(existingValue) +randomInt;
					lblResult.setText(Integer.toString(i));
					}else{				
						txtRollingPane.setText("D100");
						RollingEmpty = true;
						randomInt = (int)(Math.random() * 100+1);
						String existingValue = lblResult.getText();
						int i = Integer.valueOf(existingValue) +randomInt;
						lblResult.setText(Integer.toString(i));
					}
			}
		});
		KnapPanel.add(label7);
		label7.setIcon(new ImageIcon("res/blacksquared100.jpg"));
		
		
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
		
		lblIntegers = new JLabel("");
		lblIntegers.setBounds(23, 144, 46, 14);
		rollpanel.add(lblIntegers);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblResult.setText("0");
				txtRollingPane.setText("Nothing");
			}
		});
		btnClear.setBounds(117, 299, 89, 23);
		frame.getContentPane().add(btnClear);
	
		
		
		frame.getContentPane().add(panel);
	}
}
