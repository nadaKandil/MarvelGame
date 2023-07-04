package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;

public class PlayView extends JPanel implements ActionListener {
	
	public JFrame frame ;
	public MarvelView marvelview ; 
	public JPanel center;
	public JPanel up ; 
	public JLabel name;
	public JLabel type;
	public JLabel currentHP;
	public JLabel mana;
	public JLabel actionpoints;
	public JLabel attackdamage;
	public JLabel attackrange;
	public JLabel leader;
	public JLabel next;
	public JPanel left ; 
	public JTextArea abilitiesInfLeft  ; 
			public JPanel playersInfLeft ; 
			public JLabel p1;
			public JLabel p2;
	public JPanel down ; 
	public JPanel directionsDown ; 
	public JButton upBtn;
	public JButton downBtn;
	public JButton rightBtn;
	public JButton leftBtn;
	public JPanel actionsDown ; 
	public JButton moveBtn;
	public JButton attackBtn;
	public JButton ability1Btn;
	public JButton ability2Btn;
	public JButton ability3Btn;
	public JButton leaderabilityBtn;
	public JButton endturnBtn;
	public PlayViewListener listener;
	
	
	
	
	public PlayViewListener getListener() {
		return listener;
	}

	public void setListener(PlayViewListener listener) {
		this.listener = listener;
	}

	public PlayView(JFrame frame  , MarvelView marvelview) {
		this.frame = frame;
		this.marvelview = marvelview;
		this.setBounds(0,0,1300,700);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.red);
		
		
		center = new JPanel();
		center.setLayout(new GridLayout(0, 5 , 5,5));
		center.setBackground(new Color(137,43,43));
		this.add(center, BorderLayout.CENTER);
		
		up = new JPanel();
		up.setLayout(new GridLayout(0, 10));
		up.setPreferredSize(new Dimension( getWidth() ,50));
		up.setBackground(new Color(137,43,43));
		this.add(up, BorderLayout.NORTH);
		
		left  = new JPanel(new BorderLayout());
		left.setPreferredSize(new Dimension( 300 ,570));
		left.setBackground(new Color(137,43,43));
		this.add(left, BorderLayout.WEST);
		
		abilitiesInfLeft = new JTextArea();
		abilitiesInfLeft.setBackground(new Color(137,43,43));
		abilitiesInfLeft.setFont(new Font("Boulder" , Font.BOLD , 13));
		abilitiesInfLeft.setForeground(new Color(219,171,110));
		abilitiesInfLeft.setPreferredSize(new Dimension( 300 ,510));
		
		playersInfLeft = new JPanel();
		playersInfLeft.setPreferredSize(new Dimension( 300 ,70));
		playersInfLeft.setBackground(new Color(137,43,43));
		left.add(abilitiesInfLeft ,BorderLayout.CENTER);
		left.add(playersInfLeft , BorderLayout.SOUTH);
		
		p1 = new JLabel();
		p1.setText("P1 :" +  marvelview.startview.n1.getText());
		p1.setForeground(new Color(219,171,110));
		p1.setFont(new Font("Boulder" , Font.BOLD , 15));
		p1.setIcon(new ImageIcon("not2.png"));
		p1.setHorizontalTextPosition(JLabel.CENTER);
		p1.setVerticalTextPosition(JLabel.TOP);
		playersInfLeft.add(p1 , BorderLayout.EAST);
		
		p2 = new JLabel();
		p2.setText("P2 :" +  marvelview.startview.n2.getText());
		p2.setFont(new Font("Boulder" , Font.BOLD , 15));
		p2.setForeground(new Color(219,171,110));
		p2.setIcon(new ImageIcon("not2.png"));
		//p1.setAlignmentX(CENTER_ALIGNMENT);
		p2.setHorizontalTextPosition(JLabel.CENTER);
		p2.setVerticalTextPosition(JLabel.TOP);
		playersInfLeft.add(p2 , BorderLayout.EAST);
		
		
		
		down  = new JPanel(new BorderLayout());
		down.setPreferredSize(new Dimension( getWidth() ,70));
		down.setBackground(Color.green);
		this.add(down, BorderLayout.SOUTH);
		
		actionsDown = new JPanel();
		actionsDown.setLayout(new GridLayout(0, 7 , 5,5));
		//actionsDown .setPreferredSize(new Dimension( 70 ,300));
		actionsDown.setBackground(new Color(137,43,43));
		
		directionsDown  = new JPanel(new BorderLayout());
		directionsDown .setPreferredSize(new Dimension( 300 ,70));
		directionsDown .setBackground(Color.white);
		
		down.add(directionsDown, BorderLayout.WEST);
		down.add(actionsDown, BorderLayout.CENTER);
		
		
		createUp();
		createDown();
		
		frame.revalidate();
		frame.repaint();
	}
	
	
	public void createUp() {
		name = new JLabel("Name:");
		name.setPreferredSize(new Dimension( 90 ,40));
		name.setFont(new Font("Boulder" , Font.BOLD , 13));
		up.setBackground(new Color(219,171,110)); 
		up.add(name);
		
		type = new JLabel("Type:");
		type.setPreferredSize(new Dimension( 90 ,40));
		type.setFont(new Font("Boulder" , Font.BOLD , 13));
		type.setBackground(new Color(219,171,110)); 
		up.add(type);
		
		currentHP = new JLabel("Health Points:");
		currentHP.setPreferredSize(new Dimension( 90 ,40));
		currentHP.setFont(new Font("Boulder" , Font.BOLD , 13));
		currentHP.setBackground(new Color(219,171,110)); 
		up.add(currentHP);
		
		mana = new JLabel("Mana:");
		mana.setPreferredSize(new Dimension( 90 ,40));
		mana.setFont(new Font("Boulder" , Font.BOLD , 13));
		mana.setBackground(new Color(219,171,110)); 
		up.add(mana);
		
		actionpoints = new JLabel("Action Points:");
		actionpoints.setPreferredSize(new Dimension( 90 ,40));
		actionpoints.setFont(new Font("Boulder" , Font.BOLD , 13));
		actionpoints.setBackground(new Color(219,171,110)); 
		up.add(actionpoints);
		
		attackdamage = new JLabel("Attack Damage:");
		attackdamage.setPreferredSize(new Dimension( 90 ,40));
		attackdamage.setFont(new Font("Boulder" , Font.BOLD , 13));
		attackdamage.setBackground(new Color(219,171,110)); 
		up.add(attackdamage);
		
		attackrange = new JLabel("Attack Range:");
		attackrange.setPreferredSize(new Dimension( 90 ,40));
		attackrange.setFont(new Font("Boulder" , Font.BOLD , 13));
		attackrange.setBackground(new Color(219,171,110)); 
		up.add(attackrange);
		
		leader = new JLabel("");
		leader.setPreferredSize(new Dimension( 90 ,40));
		leader.setFont(new Font("Boulder" , Font.BOLD , 13));
		leader.setBackground(new Color(219,171,110)); 
		up.add(leader);
		
		next = new JLabel("");
		next.setPreferredSize(new Dimension( 90 ,40));
		next.setFont(new Font("Boulder" , Font.BOLD , 13));
		next.setBackground(new Color(219,171,110)); 
		up.add(next);
		
		
	}
	
	
	public void createDown() {
		upBtn = new JButton("");
		upBtn.setIcon(new ImageIcon("up3.png"));
		//upBtn.setPreferredSize(new Dimension(50,50));
		upBtn.setBackground(Color.black); 
		//upBtn.setForeground(new Color(137,43,43));
		//upBtn.setFont(new Font("Boulder" , Font.BOLD , 15));
		upBtn.addActionListener(this);
		
		downBtn = new JButton("");
		downBtn.setIcon(new ImageIcon("down3.png"));
		//downBtn.setPreferredSize(new Dimension(50,50));
		downBtn.setBackground(Color.black); 
		//downBtn.setForeground(new Color(137,43,43));
		//downBtn.setFont(new Font("Boulder" , Font.BOLD , 15));
		downBtn.addActionListener(this);
		
		rightBtn = new JButton("");
		rightBtn.setIcon(new ImageIcon("right3.png"));
		rightBtn.setPreferredSize(new Dimension(150,50));
		rightBtn.setBackground(Color.black); 
		//rightBtn.setForeground(new Color(137,43,43));
		//rightBtn.setFont(new Font("Boulder" , Font.BOLD , 15));
		rightBtn.addActionListener(this);
		
		leftBtn = new JButton("");
		leftBtn.setIcon(new ImageIcon("left3.png"));
		leftBtn.setPreferredSize(new Dimension(150,50));
		leftBtn.setBackground(Color.black); 
		//leftBtn.setForeground(new Color(137,43,43));
		//leftBtn.setFont(new Font("Boulder" , Font.BOLD , 15));
		leftBtn.addActionListener(this);
		
		directionsDown.add(rightBtn , BorderLayout.EAST);
		directionsDown.add(leftBtn , BorderLayout.WEST);
		directionsDown.add(upBtn , BorderLayout.NORTH);
		directionsDown.add(downBtn , BorderLayout.SOUTH);
		
		moveBtn = new JButton("MOVE");
		moveBtn.setBackground(new Color(219,171,110)); 
		moveBtn.setForeground(new Color(137,43,43));
		moveBtn.setFont(new Font("Boulder" , Font.BOLD , 15));
		moveBtn.addActionListener(this);
		
		attackBtn = new JButton("ATTACK");
		attackBtn.setBackground(new Color(219,171,110)); 
		attackBtn.setForeground(new Color(137,43,43));
		attackBtn.setFont(new Font("Boulder" , Font.BOLD , 15));
		attackBtn.addActionListener(this);
		
		ability1Btn = new JButton("ABILITY1");
		ability1Btn.setBackground(new Color(219,171,110)); 
		ability1Btn.setForeground(new Color(137,43,43));
		ability1Btn.setFont(new Font("Boulder" , Font.BOLD , 15));
		ability1Btn.addActionListener(this);
		
		ability2Btn = new JButton("ABILITY2");
		ability2Btn.setBackground(new Color(219,171,110)); 
		ability2Btn.setForeground(new Color(137,43,43));
		ability2Btn.setFont(new Font("Boulder" , Font.BOLD , 15));
		ability2Btn.addActionListener(this);
		
		ability3Btn = new JButton("ABILITY3");
		ability3Btn.setBackground(new Color(219,171,110)); 
		ability3Btn.setForeground(new Color(137,43,43));
		ability3Btn.setFont(new Font("Boulder" , Font.BOLD , 15));
		ability3Btn.addActionListener(this);
		
		leaderabilityBtn = new JButton("LEADER ABILITY");
		leaderabilityBtn.setBackground(new Color(219,171,110)); 
		leaderabilityBtn.setForeground(new Color(137,43,43));
		leaderabilityBtn.setFont(new Font("Boulder" , Font.BOLD , 15));
		leaderabilityBtn.addActionListener(this);
		
		endturnBtn = new JButton("END TURN");
		endturnBtn.setBackground(new Color(219,171,110)); 
		endturnBtn.setForeground(new Color(137,43,43));
		endturnBtn.setFont(new Font("Boulder" , Font.BOLD , 15));
		endturnBtn.addActionListener(this);
		
		actionsDown.add(moveBtn);
		actionsDown.add(attackBtn);
		actionsDown.add(ability1Btn);
		actionsDown.add(ability2Btn);
		actionsDown.add(ability3Btn);
		actionsDown.add(leaderabilityBtn);
		actionsDown.add(endturnBtn);
		
		
	}

	
	public void addBtn(JButton Btn) {
		center.add(Btn);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton current = (JButton)e.getSource();
		if ( current == moveBtn)
			listener.onMoveBtn();
		if ( current == attackBtn)
			listener.onAttackBtn();
		if (current == ability1Btn)
			try {
				listener.onAbility1Btn();
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e3) {
				JOptionPane.showMessageDialog(null ,e3.getMessage());
			}
		if (current == ability2Btn)
			try {
				listener.onAbility2Btn();
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e3) {
				JOptionPane.showMessageDialog(null ,e3.getMessage());
			}
		if (current == ability3Btn)
			try {
				listener.onAbility3Btn();
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e3) {
				JOptionPane.showMessageDialog(null ,e3.getMessage());
			}
		if (current == leaderabilityBtn)
			try {
				listener.onLeaderAbilityBtn();
			} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e3) {
				JOptionPane.showMessageDialog(null ,e3.getMessage());
			}
		if ( current == endturnBtn)
			listener.onEndTurnBtn();
		if ( current == upBtn)
			try {
				listener.onUpBtn();
			} catch (NotEnoughResourcesException e2) {
				JOptionPane.showMessageDialog(null ,e2.getMessage());
			} catch (UnallowedMovementException e2) {
				JOptionPane.showMessageDialog(null ,e2.getMessage());
			} catch (ChampionDisarmedException e2) {
				JOptionPane.showMessageDialog(null ,e2.getMessage());
			} catch (InvalidTargetException e2) {
				JOptionPane.showMessageDialog(null ,e2.getMessage());
			} catch (CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null ,e1.getMessage());
			} catch (AbilityUseException e1) {
				JOptionPane.showMessageDialog(null ,e1.getMessage());
			}
		if ( current == downBtn)
					try {
						listener.onDownBtn();
					} catch (AbilityUseException e1) {
						JOptionPane.showMessageDialog(null ,e1.getMessage());
					} catch (CloneNotSupportedException e1) {
						JOptionPane.showMessageDialog(null ,e1.getMessage());
					} catch (ChampionDisarmedException | InvalidTargetException e1) {
						JOptionPane.showMessageDialog(null ,e1.getMessage());
					}catch (NotEnoughResourcesException | UnallowedMovementException e1) {
						JOptionPane.showMessageDialog(null ,e1.getMessage());
			}
		if( current == rightBtn)
				try {
					listener.onRightBtn();
				} catch (ChampionDisarmedException | InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null ,e1.getMessage());
				} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
					JOptionPane.showMessageDialog(null ,e1.getMessage());
				} catch (AbilityUseException e1) {
					JOptionPane.showMessageDialog(null ,e1.getMessage());
				} catch (CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null ,e1.getMessage());
				}
		if( current == leftBtn)
				try {
					listener.onLeftBtn();
				} catch (ChampionDisarmedException | InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null ,e1.getMessage());
				}catch (NotEnoughResourcesException | UnallowedMovementException e1) {
					JOptionPane.showMessageDialog(null ,e1.getMessage());
				} catch (AbilityUseException e1) {
					JOptionPane.showMessageDialog(null ,e1.getMessage());
				} catch (CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null ,e1.getMessage());
				}
		
	}

	
}
