package views;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class StartView extends JPanel implements ActionListener {
	
	public JFrame frame ;
	public MarvelView marvelview;
	public JTextField n1;
	public JTextField n2;
	public StartViewListener listener ;
	
	public StartViewListener getListener() {
		return listener;
	}

	public void setListener(StartViewListener listener) {
		this.listener = listener;
	}

	public StartView(  JFrame frame  , MarvelView marvelview) {
		
		this.frame = frame;
		this.marvelview = marvelview;
		JLabel bck = new JLabel (" ",new ImageIcon("background.jpg"), JLabel.CENTER);
		bck.setBounds(0,0,1300,700);

		JButton start = new JButton ("START");
		start.setBounds(560,550,200,60);
		start.setFont(new Font("Boulder" , Font.BOLD , 20));
		start.setForeground(new Color(219,171,110));
		start.setBackground(new Color(137,43,43));
		start.addActionListener(this);
		
		JLabel p1 = new JLabel("PLAYER1 ENTER NAME ");
		p1.setFont(new Font("Boulder" , Font.BOLD , 20));
		p1.setBounds(550,200,400,70);
		p1.setBackground(new Color(137,43,43));
		p1.setForeground(new Color(219,171,110));
	    n1 = new JTextField ("");
	    n1.setForeground(new Color(137,43,43));
		n1.setBounds(560, 250,200,50);
		n1.setFont(new Font("Boulder" , Font.BOLD , 25));
		
		JLabel p2 = new JLabel("PLAYER2 ENTER NAME ");
		p2.setFont(new Font("Boulder" , Font.BOLD , 20));
		p2.setBounds(550,300,400,70);
		p2.setBackground(new Color(137,43,43));
		p2.setForeground(new Color(219,171,110));
	    n2 = new JTextField ("");
	    n2.setForeground(new Color(137,43,43));
		n2.setBounds(560, 350,200,50);
		n2.setFont(new Font("Boulder" , Font.BOLD , 25));

		this.setLayout(new BorderLayout());
		this.add(p1 );
	    this.add(n1 );
		this.add(p2 );
		this.add(n2 );
		this.add(start );
		this.add(bck);
		this.setSize(1300,700);
		frame.add(this);
		frame.revalidate();
		frame.repaint();
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (n1.getText() .equals("") ) 
			JOptionPane.showMessageDialog(null , " OPS ! you have not enter player1 name ");		
		else if (n2.getText() .equals("") ) 
			JOptionPane.showMessageDialog(null , " OPS ! you have not enter player2 name ");	
		
		else {
		try {
			listener.newGame(n1.getText(), n2.getText());
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		frame.remove(this);
		frame.add(marvelview.chooseview);
		JOptionPane.showMessageDialog(null , " NEXT ! EACH PLAYER IS REQUIRED TO CHOOSE FIRSTLY TEAM LEADER THEN 2 CHAMPIONS");	
		frame.revalidate();
		frame.repaint();
		
		}
		
	}

}
