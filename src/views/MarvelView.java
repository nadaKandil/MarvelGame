package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MarvelView  {
	
	public  StartView  startview ;
	public ChooseView chooseview;
	public PlayView playview;
	public FinalView finalview;
	public JFrame frame ;
	
	
	public MarvelView() {

		frame = new JFrame() ;
		
		ImageIcon icon = new ImageIcon("icon4.jpg");
		frame.setIconImage(icon.getImage());
		frame.setTitle("MARVEL");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		
		frame.setBounds(0, 0, 1300, 700);
		frame.setLayout(null);
	    startview = new StartView(frame , this );
	    chooseview = new ChooseView( frame , this);
		
	    
		frame.revalidate();
		frame.repaint();
		
		}		
	
}
