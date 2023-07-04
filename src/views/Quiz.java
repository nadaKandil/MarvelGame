package views;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.Game;
import engine.Player;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.Champion;

public class Quiz implements ActionListener {

	public JFrame frame ;
	public JPanel panel;
	public Game game;
	public JTextField abName;
	public  JTextField abType;
	public int c;
	public JTextField counter;
	public JButton next;
	
	
	public Quiz() throws IOException {
		frame = new JFrame();
		frame.setTitle("QUIZ");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1300, 700);
		
		game = new Game (new Player("nada"), new Player("laila"));
		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");
		
		panel =  new JPanel ();
		panel.setLayout(new GridLayout(0, 2, 4 ,4 ));
		frame.add(panel);

		abName = new JTextField();
		abName.setFont(new Font("Boulder" , Font.BOLD , 30));
		abType = new JTextField();
		abType.setFont(new Font("Boulder" , Font.BOLD , 30));
		counter = new JTextField();
		counter.setFont(new Font("Boulder" , Font.BOLD , 30));
		next = new JButton();
		next.setText("NEXT");
		next.setFont(new Font("Boulder" , Font.BOLD , 30));
		next.addActionListener(this);
		
		panel.add(abName);
		panel.add(abType);
		panel.add(counter);
		panel.add(next);
		
		frame.revalidate();
		frame.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		c = (int) (Math.random() * 45);
		Ability a = Game.getAvailableAbilities().get(c);
		
		abName.setText(a.getName());
		if ( a instanceof HealingAbility)
			abType.setText("Healing Ability");
		if ( a instanceof DamagingAbility)
			abType.setText("Damaging Ability");
		if ( a instanceof CrowdControlAbility)
			abType.setText("CrowdContro Ability");
		counter.setText(""+ c);   
		
	}
	
	public static void main(String[] args) throws IOException {
		new Quiz();
	}
	
}
