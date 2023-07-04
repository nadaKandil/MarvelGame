package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;

public class ChooseView extends JPanel implements ActionListener {
	
	public JFrame frame ;
	public MarvelView marvelview ; 
	public JPanel champions ;
	public JPanel chmpInf ; 
	public JTextArea info;
	public JButton chooseBtn ; 
	public ChooseBtnListener listener;
	
	public ChooseView(JFrame frame  , MarvelView marvelview) {
		
		this.frame = frame;
		this.marvelview = marvelview;
		this.setBounds(0,0,1300,700);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.red);
		frame.revalidate();
		frame.repaint();
		this.setLayout(new BorderLayout());
		champions =  new JPanel ();
		champions.setLayout(new GridLayout(0, 5 , 4 ,4 ));
		champions.setBackground(new Color(137,43,43));
		champions.setPreferredSize(new Dimension(1100,this.getHeight()));
		champions.setBounds(200, 0, this.getWidth(),this.getHeight()) ;
		
	
		
		chmpInf = new JPanel(new BorderLayout());
		chmpInf.setPreferredSize(new Dimension( 200 , getHeight()));
		chmpInf.setBounds(0, 0, 200, this.getHeight());
		chmpInf.setBackground(new Color(137,43,43));
		
		info = new JTextArea();
		info.setPreferredSize(new Dimension(650,200));
		info.setEditable(false);
		info.setFont(new Font("Boulder" , Font.PLAIN , 15));
		info.setBackground(new Color(137,43,43));
		info.setForeground(new Color(219,171,110));
		
		chooseBtn = new JButton("CHOOSE");
		chooseBtn.setPreferredSize(new Dimension(200,150));
		chooseBtn.setBackground(new Color(137,43,43));
		chooseBtn.setForeground(new Color(219,171,110));
		chooseBtn.setFont(new Font("Boulder" , Font.BOLD , 30));
		chooseBtn.addActionListener(this);
	
		chmpInf.add(info);
		chmpInf.add(chooseBtn, BorderLayout.SOUTH);
		this.add(chmpInf, BorderLayout.WEST);
		this.add(champions, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}
	
	public void addBtn(JButton chBtn) {
		champions.add(chBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		listener.onChoose();
	}

	
	
	public ChooseBtnListener getListener() {
		return listener;
	}

	public void setListener(ChooseBtnListener listener) {
		this.listener = listener;
	}
	
	
	
	

}
