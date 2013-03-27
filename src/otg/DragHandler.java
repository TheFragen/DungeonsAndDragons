package otg;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class DragHandler implements MouseListener {
	public void mousePressed(MouseEvent e) {
	    JComponent c = (JComponent) e.getSource();
	    TransferHandler handler = c.getTransferHandler();
	    handler.exportAsDrag(c, e, TransferHandler.COPY);
	  }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
