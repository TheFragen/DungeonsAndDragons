package otg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ViewImage extends JPanel {
	
	private static final long serialVersionUID = -3792044521348924868L;

	
	public ViewImage(String file) throws IOException {
		setLayout(null);

		BufferedImage img = ImageIO.read(new File(file));
		
		ImagePanel panel = new ImagePanel(
				new ImageIcon(img).getImage());
		panel.setLocation(0, 40);
		
        add(panel);
	}
	
	
}
