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

public class Quiz2  implements ActionListener{
	public JFrame frame ;
	public JPanel panel;
	public JTextField abName;
	public  JTextField abType;
	public int c;
	public JTextField counter;
	public JButton next;
	public nextListener listener;
	
	public Quiz2()  {
		frame = new JFrame();
		frame.setTitle("QUIZ2");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1300, 700);

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

	public nextListener getListener() {
		return listener;
	}

	public void setListener(nextListener listener) {
		this.listener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
		if (e.getSource() == next)
			try {
				listener.onNext();
				
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}

	}

}
