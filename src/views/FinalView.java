package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FinalView extends JPanel {
	public JFrame frame ;
	public MarvelView marvelview;
	public JLabel winnerName ;
	public JButton finishBtn;
	
	
	public FinalView (JFrame frame , MarvelView marvelViewZ,String winner) {
		this.frame = frame;
		this.marvelview = marvelview;
		
		this.setBounds(0,0,1300,700);
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(137,43,43));
		
		winnerName = new JLabel();
		winnerName.setFont(new Font("Boulder" , Font.BOLD , 40));
		winnerName.setText(winner  + " is the winner !!!");
		winnerName.setForeground(new Color(219,171,110));
		
		this.add(winnerName, BorderLayout.CENTER);
		
		frame.add(this);
		frame.revalidate();
		frame.repaint();
		
		
		
	}

}
