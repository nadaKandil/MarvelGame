package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.Game;
import engine.Player;
import model.world.Champion;

public class Quiz3 implements ActionListener {
	
	public JFrame frame;
	public Game game;
	public JPanel panel; 
	public Champion champ1;
	public JButton ch1 ; 
	public Champion champ2;
	public JButton ch2 ; 
	public Champion champ3;
	public JButton ch3 ; 
	
	public int hp1;
	public int hp2;
	public int hp3;
	
	public Quiz3() throws IOException {
		frame = new JFrame();
		frame.setTitle("QUIZ3");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1300, 700);
		
		game = new Game (new Player("l")   ,  new Player("k"));
		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");
		
		panel =  new JPanel ();
		panel.setLayout(new GridLayout(0, 1, 4 ,4 ));
		frame.add(panel);
		
		 champ1 = Game.getAvailableChampions().get((int) (Math.random() * 15));
		hp1 = (int)champ1.getCurrentHP();
		
		champ2 = Game.getAvailableChampions().get((int) (Math.random() * 15));
		hp2 =(int) champ2.getCurrentHP();
		

		champ3 = Game.getAvailableChampions().get((int) (Math.random() * 15));
		hp3 = (int)champ3.getCurrentHP();
		
		
		
		ch1 = new JButton();
		ch1.setText(champ1.getName() +  hp1);
		ch1.addActionListener(this);
		
		ch2 = new JButton();
		ch2.setText(champ2.getName() +hp2 );
		ch2.addActionListener(this);
		
		ch3 = new JButton();
		ch3.setText(champ3.getName() +  hp3 );
		ch3.addActionListener(this);
		
		
		panel.add(ch1);		
		panel.add(ch2);
		panel.add(ch3);
		
		frame.revalidate();
		frame.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		
		if ( e.getSource() == ch1) {
			
			hp1 -= 500;
			ch1.setText( champ1.getName() + hp1);
		}
		else if ( e.getSource() == ch2) {
			hp2 -= 500;
			ch2.setText( champ2.getName() + " "  +hp2);
		}
		else if ( e.getSource() == ch3) {
			hp3 -= 500;
			ch3.setText( champ3.getName() +" "  + hp3);
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		new Quiz3();
	}

}
