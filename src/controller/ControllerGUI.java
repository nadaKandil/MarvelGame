package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import engine.Game;
import engine.Player;
import engine.PriorityQueue;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;
import views.ChooseBtnListener;
import views.FinalView;
import views.MarvelView;
import views.PlayView;
import views.PlayViewListener;
import views.StartViewListener;

public class ControllerGUI implements StartViewListener, ActionListener, ChooseBtnListener , PlayViewListener {
	
	private Game model;
	private MarvelView marvelview;
	private ArrayList<JButton> champsBtns;
	private ArrayList logos;
	public JButton oldBtn ; 
	public JButton oldBtn2 ; 
	public int count = 0 ; 
	public JButton [][]  array = new JButton[5][5]; 
	
	
	public ControllerGUI() {
		
		this.marvelview = new MarvelView();
		marvelview.startview.setListener(this);
		marvelview.chooseview.setListener(this);
	
		champsBtns = new ArrayList<>();	
		
	}
	
	public void addlogos() {
		logos = new ArrayList<>() ;
		logos.add(new ImageIcon("captainamerica2.png"));
		logos.add(new ImageIcon("deadpool2.png"));
		logos.add(new ImageIcon("drstrange2.png"));
		logos.add(new ImageIcon("electro2.png"));
		logos.add(new ImageIcon("ghostrider2.png"));
		logos.add(new ImageIcon("hela2.png"));
		logos.add(new ImageIcon("hulk2.png"));
		logos.add(new ImageIcon("iceman2.png"));
		logos.add(new ImageIcon("ironman2.png"));
		logos.add(new ImageIcon("loki2.png"));
		logos.add(new ImageIcon("quicksilver2.png"));
		logos.add(new ImageIcon("spiderman2.png"));
		logos.add(new ImageIcon("thor2.png"));
		logos.add(new ImageIcon("venom.jpg"));
		logos.add(new ImageIcon("yellowjacket2.png"));
	}

	@Override
	public void newGame(String n1, String n2) throws IOException {
		model = new Game(new Player(n1),new Player(n2));
		
		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");
		addlogos();
		for (int i = 0 ; i < model.getAvailableChampions().size() ; i++) {
			Champion chmp = model.getAvailableChampions().get(i);
			JButton chBtn = new JButton();
			chBtn.setText(chmp.getName());
			chBtn.setIcon((Icon) logos.get(i));
			chBtn.setBackground(Color.black);
			chBtn.setForeground(new Color(219,171,110));
			chBtn.setFont(new Font("Boulder" , Font.BOLD , 15));
			marvelview.frame.revalidate();
			marvelview.frame.repaint();
			
			chBtn.addActionListener(this);
			
			marvelview.chooseview.addBtn(chBtn);
			champsBtns.add(chBtn);
	
		}
		
	}

	public static void main(String[] args) {
		new ControllerGUI()	;
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton currentBtn = (JButton) e.getSource();
		oldBtn = currentBtn;
		
		if ( champsBtns.contains(currentBtn)) {
			int i = champsBtns.indexOf(currentBtn);
			marvelview.chooseview.info.setText(model.getAvailableChampions().get(i).toString());
		}
		else if (oldBtn2 == marvelview.playview.ability1Btn  &&  (model.getCurrentChampion().getAbilities().get(0)).getCastArea().equals(AreaOfEffect.SINGLETARGET)   ) {
			ArrayList index = getIndex(currentBtn);
			try {
				model.castAbility(model.getCurrentChampion().getAbilities().get(0), (int)index.get(0),(int) index.get(1));
			} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
					| CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null ,e1.getMessage());
			}
		}
		else if (oldBtn2 == marvelview.playview.ability2Btn &&  (model.getCurrentChampion().getAbilities().get(1)).getCastArea().equals(AreaOfEffect.SINGLETARGET) ) {
			ArrayList index = getIndex(currentBtn);
			try {
				model.castAbility(model.getCurrentChampion().getAbilities().get(1), (int)index.get(0),(int) index.get(1));
			} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
					| CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null ,e1.getMessage());
			}
		}
		else if (oldBtn2 == marvelview.playview.ability3Btn &&  (model.getCurrentChampion().getAbilities().get(2)).getCastArea().equals(AreaOfEffect.SINGLETARGET)) {
			ArrayList index = getIndex(currentBtn);
			try {
				model.castAbility(model.getCurrentChampion().getAbilities().get(2), (int)index.get(0),(int) index.get(1));
			} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
					| CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null ,e1.getMessage());
			}
		}
		
		
		else {
			ArrayList ij = new ArrayList();
			ij = getIndex(currentBtn);
			int i = (int) ij.get(0);
			int j = (int) ij.get(1);
			 Damageable x = (Damageable)model.board[i][j];
			
			 if( x instanceof Champion ) {
				 marvelview.playview.abilitiesInfLeft.setText(setAbilitiesInfo((Champion)x ));
				 
				 marvelview.playview.name.setText( "Name: " +((Champion)x).getName() );
				 marvelview.playview.currentHP.setText("Health points:"+ ((Champion)x).getCurrentHP());
				 marvelview.playview.mana.setText("Mana:"+ ((Champion)x).getMana());
				 marvelview.playview.actionpoints.setText( "Action points: "+ ((Champion)x).getCurrentActionPoints());
				 marvelview.playview.attackdamage.setText("Attack damage: "+ ((Champion)x).getAttackDamage());
				 marvelview.playview.attackrange.setText("Attack range: "+ ((Champion)x).getAttackRange());
				 if ( ((Champion)x) instanceof Hero)
					 marvelview.playview.type.setText("Type: Hero");
				 if ( ((Champion)x) instanceof Villain)
					 marvelview.playview.type.setText("Type: Villain");
				 if ( ((Champion)x) instanceof AntiHero)
					 marvelview.playview.type.setText("Type: AntiHero");
				 
				 if ( ((Champion)x).equals(model.getFirstPlayer().getLeader())   ||  ((Champion)x).equals(model.getSecondPlayer().getLeader()) ) 
					 marvelview.playview.leader.setText("LEADER");
				 else 
					 marvelview.playview.leader.setText("NOT LEADER");
			 }
			 else  if( x instanceof Cover ) {
				 marvelview.playview.abilitiesInfLeft.setText("");
				 marvelview.playview.name.setText( "" );
				 marvelview.playview.currentHP.setText("Health points:"+ ((Cover)x).getCurrentHP());
				 marvelview.playview.mana.setText("");
				 marvelview.playview.actionpoints.setText( "");
				 marvelview.playview.attackdamage.setText(" ");
				 marvelview.playview.attackrange.setText(" ");
				 marvelview.playview.type.setText("Type: Cover");
				 marvelview.playview.leader.setText("");
			 }
			 else {
				 marvelview.playview.abilitiesInfLeft.setText("");
				 marvelview.playview.name.setText( "" );
				 marvelview.playview.currentHP.setText("");
				 marvelview.playview.mana.setText("");
				 marvelview.playview.actionpoints.setText( "");
				 marvelview.playview.attackdamage.setText(" ");
				 marvelview.playview.attackrange.setText(" ");
				 marvelview.playview.type.setText("");
				 marvelview.playview.leader.setText("");
			 }
			
		}

	}
	
	
	public String setAbilitiesInfo ( Champion c) {
		 String s = "";
		 for ( int k = 0 ; k < c.getAbilities().size() ; k++ ) {
			 s+=  "       ABILITY" + (k+1) + " :"    + c.getAbilities().get(k).getName() + "\n"  +
					 "Mana Cost: " +c.getAbilities().get(k).getManaCost()  + "\n" +
					 "Base Cooldown:  " +c.getAbilities().get(k).getBaseCooldown()  + "\n" +
					 "Current Cooldown:  " +c.getAbilities().get(k).getCurrentCooldown()  + "\n" +
					 "Cast Range: :  " +c.getAbilities().get(k).getCastRange()  + "\n" +
					 "Required Actions Points: :  " +c.getAbilities().get(k).getRequiredActionPoints()  + "\n" +
					 "Area of Effect :  " +c.getAbilities().get(k).getCastArea() + "\n"  ;
			 if ( c.getAbilities().get(k)   instanceof HealingAbility)
				 s+= "Heal Amount: " + ((HealingAbility)(c.getAbilities().get(k))).getHealAmount() +"\n"  ;
			 if ( c.getAbilities().get(k)   instanceof DamagingAbility)
				 s+= "Damage Amount: " + ((DamagingAbility)(c.getAbilities().get(k))).getDamageAmount() +"\n" ;
			 if ( c.getAbilities().get(k)   instanceof CrowdControlAbility)
				 s+= "Abilities's Effect:  " + ((CrowdControlAbility)(c.getAbilities().get(k))).getEffect().getName() +"\n" ;
		 }
		 s+="-----------" + "\n";
		 for( int j = 0 ; j < c.getAppliedEffects().size() ; j++) {
			 s+=  "       EFFECT NAME: " +  c.getAppliedEffects().get(j).getName() + "\n" +
					 "Duration of Effect: " + c.getAppliedEffects().get(j).getDuration();
		 }
		 return s;
	}
	

	public ArrayList getIndex( JButton btn) {
		ArrayList< Integer > index = new ArrayList(2);
		
		for (int i = 0 ; i<=4 ; i++) {
			for (int j = 0 ; j<=4 ; j++) {
				if (array[i][j] == btn) {
					   index.add(i);
					   index.add(j);
				}
			}
		}
		return index;
	}
	
	@Override

	public void onChoose() {
		int x = champsBtns.indexOf(oldBtn);
		
		if (count < 3) {
			model.getFirstPlayer().getTeam().add(model.getAvailableChampions().get(x));
			count++;  
			oldBtn.setEnabled(false);
			System.out.println(count);}
		else if(  count >= 3  && count <6) {
			model.getSecondPlayer().getTeam().add(model.getAvailableChampions().get(x));
			count++;   
			oldBtn.setEnabled(false); }
		
		if (count == 3 )
			JOptionPane.showMessageDialog(null , marvelview.startview.n1.getText()+" you have chosen your team");	
		if ( count == 6) {
			JOptionPane.showMessageDialog(null , marvelview.startview.n2.getText()+" you have chosen your team");	
			model.getFirstPlayer().setLeader(model.getFirstPlayer().getTeam().get(0));
			model.getSecondPlayer().setLeader(model.getSecondPlayer().getTeam().get(0));
			model.placeChampions();
			model.prepareChampionTurns();
			marvelview.frame.remove(marvelview.chooseview);
			marvelview.playview = new PlayView( marvelview.frame , marvelview);
			
			
			for ( int i = 4 ; i >=0 ; i--) {
				for(int j = 0 ; j <=4 ; j++) {
					JButton Btn = new JButton();
					Btn.setBackground(Color.black);
					Btn.setForeground(new Color(219,171,110));
					Btn.setFont(new Font("Boulder" , Font.BOLD , 15));
					
					Btn.addActionListener(this);
					
					Btn.setFocusable(false);
					
					 if ( model.board[i][j]  instanceof Champion) {
						int img =  model.getAvailableChampions().indexOf((Champion)model.board[i][j]);
						Btn.setIcon((Icon) logos.get(img));
						array[i][j] = Btn ;
						if ( model.getFirstPlayer().getTeam().contains((Champion)model.board[i][j]))
							Btn.setText("team1");
						if ( model.getSecondPlayer().getTeam().contains((Champion)model.board[i][j]))
							Btn.setText("team2");
						if((Champion)model.board[i][j] == model.getCurrentChampion())
							Btn.setText("current");
						else
							Btn.setText("");
					}
					
					else if ( model.board[i][j]  instanceof Cover) {
						Btn.setIcon(new ImageIcon("cover2.jpg"));
						array[i][j] = Btn;
					}
					
					else {
						Btn.setText("");
						array[i][j] = Btn;}
						
					
					marvelview.playview.addBtn(Btn);
					marvelview.frame.revalidate();
					marvelview.frame.repaint();
				}
			}
			marvelview.frame.add(marvelview.playview);
			marvelview.playview.setListener(this);
			marvelview.frame.revalidate();
			marvelview.frame.repaint();
			
		}
			
			
		}

	@Override
	public void onMoveBtn() {
		JOptionPane.showMessageDialog(null , " PLEASE CHOOSE WHICH DIRECTION TO MOVE IN ");
		oldBtn2  = marvelview.playview.moveBtn;
	}

	@Override
	public void onAttackBtn() {
		JOptionPane.showMessageDialog(null , " PLEASE CHOOSE WHICH DIRECTION TO ATTACK IN");
		oldBtn2 = marvelview.playview.attackBtn;
	}

	@Override
	public void onAbility1Btn() throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException {
		Ability a =model.getCurrentChampion().getAbilities().get(0);
		
		if ( a.getCastArea().equals(AreaOfEffect.SELFTARGET)   ||     a.getCastArea().equals(AreaOfEffect.TEAMTARGET)   || a.getCastArea().equals(AreaOfEffect.SURROUND) )
			model.castAbility(model.getCurrentChampion().getAbilities().get(0));
		
		if ( a.getCastArea().equals(AreaOfEffect.DIRECTIONAL)) {
			JOptionPane.showMessageDialog(null , " PLEASE CHOOSE WHICH DIRECTION TO CAST THE ABILITY IN");
			oldBtn2 = marvelview.playview.ability1Btn;
		}
		if  ( a.getCastArea().equals(AreaOfEffect.SINGLETARGET)) {
			JOptionPane.showMessageDialog(null , " PLEASE CHOOSE WHICH TARGET YOU WANT TO CAST ABILITY ON");
			oldBtn2 =  marvelview.playview.ability1Btn;
		}
				checkGameOver();
	}

	@Override
	public void onAbility2Btn() throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException {
		Ability a =model.getCurrentChampion().getAbilities().get(1);
		
		if ( a.getCastArea().equals(AreaOfEffect.SELFTARGET)   ||     a.getCastArea().equals(AreaOfEffect.TEAMTARGET)   || a.getCastArea().equals(AreaOfEffect.SURROUND) )
			model.castAbility(model.getCurrentChampion().getAbilities().get(1));
		
		if ( a.getCastArea().equals(AreaOfEffect.DIRECTIONAL)) {
			JOptionPane.showMessageDialog(null , " PLEASE CHOOSE WHICH DIRECTION TO CAST THE ABILITY IN");
			oldBtn2 = marvelview.playview.ability2Btn;
		}
		if  ( a.getCastArea().equals(AreaOfEffect.SINGLETARGET)) {
			JOptionPane.showMessageDialog(null , " PLEASE CHOOSE WHICH TARGET YOU WANT TO CAST ABILITY ON");
			oldBtn2 =  marvelview.playview.ability2Btn;
		}
		checkGameOver();
	}

	@Override
	public void onAbility3Btn() throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException {
		Ability a =model.getCurrentChampion().getAbilities().get(2);
		
		if ( a.getCastArea().equals(AreaOfEffect.SELFTARGET)   ||     a.getCastArea().equals(AreaOfEffect.TEAMTARGET)   || a.getCastArea().equals(AreaOfEffect.SURROUND) )
			model.castAbility(model.getCurrentChampion().getAbilities().get(2));
		
		if ( a.getCastArea().equals(AreaOfEffect.DIRECTIONAL)) {
			JOptionPane.showMessageDialog(null , " PLEASE CHOOSE WHICH DIRECTION TO CAST THE ABILITY IN");
			oldBtn2 = marvelview.playview.ability3Btn;
		}
		
		if  ( a.getCastArea().equals(AreaOfEffect.SINGLETARGET)) {
			JOptionPane.showMessageDialog(null , " PLEASE CHOOSE WHICH TARGET YOU WANT TO CAST ABILITY ON");
			oldBtn2 =  marvelview.playview.ability3Btn;
		}
		checkGameOver();
	}

	@Override
	public void onLeaderAbilityBtn() throws LeaderNotCurrentException, LeaderAbilityAlreadyUsedException {
		model.useLeaderAbility();
		if ( model.isFirstLeaderAbilityUsed())
			marvelview.playview.p1.setIcon(new ImageIcon("used2.png"));
		if ( model.isSecondLeaderAbilityUsed())
			marvelview.playview.p2.setIcon(new ImageIcon("used2.png"));
		checkGameOver();
	}
	
	public void refresh() {
		for ( int i = 4 ; i >=0 ; i--) {
			for(int j = 0 ; j <=4 ; j++) {
				JButton  Btn= (JButton)array[i][j];
				 if ( model.board[i][j]  instanceof Champion) {
					int img =  model.getAvailableChampions().indexOf((Champion)model.board[i][j]);
					Btn.setIcon((Icon) logos.get(img));
					if ( model.getFirstPlayer().getTeam().contains((Champion)model.board[i][j]))
						Btn.setText("team1");
					if ( model.getSecondPlayer().getTeam().contains((Champion)model.board[i][j]))
						Btn.setText("team2");
					if((Champion)model.board[i][j] == model.getCurrentChampion())
						Btn.setText("current");
					else
						Btn.setText("");
				}	
				else if ( model.board[i][j]  instanceof Cover) {
					Btn.setIcon(new ImageIcon("cover2.jpg"));
					Btn.setBackground(Color.black);
				}
				
				else   {
					Btn.setText("");
					Btn.setIcon(new ImageIcon("null2.png"));
					Btn.setBackground(Color.black);
				}
				 checkGameOver();
				marvelview.frame.revalidate();
				marvelview.frame.repaint();
			}
		}
	//	Champion next = getNext();
	//	marvelview.playview.next.setText("NEXT:"  + next.getName());
		//marvelview.playview.next.setIcon((Icon) logos.get(0));
	}
	
	/*public Champion  getNext() {
		
		PriorityQueue tmp = new PriorityQueue(1);
		if(model.getTurnOrder().isEmpty())
			model.prepareChampionTurns();
		Champion current = (Champion) model.getTurnOrder().remove();
		tmp.insert(current);
		if(model.getTurnOrder().isEmpty())
			model.prepareChampionTurns();
		Champion NextPlayer = (Champion) model.getTurnOrder().peekMin();
		model.getTurnOrder().insert(current);
		return NextPlayer;
	} */

	@Override
	public void onEndTurnBtn() {
		model.endTurn();
		checkGameOver();
		refresh();
		
	}

	@Override
	public void onUpBtn() throws NotEnoughResourcesException, UnallowedMovementException, ChampionDisarmedException, InvalidTargetException, AbilityUseException, CloneNotSupportedException {
		if ( oldBtn2 == marvelview.playview.moveBtn) {
			model.move(Direction.UP);
			refresh();  
			}
		if ( oldBtn2 == marvelview.playview.attackBtn) {
			model.attack(Direction.UP);
			refresh();  
			}
		if ( oldBtn2 == marvelview.playview.ability1Btn) {
			model.castAbility(model.getCurrentChampion().getAbilities().get(0)  , Direction.UP);
			refresh();  
			}
		if ( oldBtn2 == marvelview.playview.ability2Btn) {
			model.castAbility(model.getCurrentChampion().getAbilities().get(1)  , Direction.UP);
			refresh();  
			}
		if ( oldBtn2 == marvelview.playview.ability3Btn) {
			model.castAbility(model.getCurrentChampion().getAbilities().get(2)  , Direction.UP);
			refresh();  
			}
		checkGameOver();
	}

	@Override
	public void onDownBtn() throws NotEnoughResourcesException, UnallowedMovementException, ChampionDisarmedException, InvalidTargetException, AbilityUseException, CloneNotSupportedException {
		if ( oldBtn2 == marvelview.playview.moveBtn) {
			model.move(Direction.DOWN);
			refresh();  }
		if ( oldBtn2 == marvelview.playview.attackBtn) {
			model.attack(Direction.DOWN);
			refresh();  
			}
		if ( oldBtn2 == marvelview.playview.ability1Btn) {
			model.castAbility(model.getCurrentChampion().getAbilities().get(0)  , Direction.DOWN);
			refresh();  
			}
		if ( oldBtn2 == marvelview.playview.ability2Btn) {
			model.castAbility(model.getCurrentChampion().getAbilities().get(1)  , Direction.DOWN);
			refresh();  
			}
		if ( oldBtn2 == marvelview.playview.ability3Btn) {
			model.castAbility(model.getCurrentChampion().getAbilities().get(2)  , Direction.DOWN);
			refresh();  
			}
		checkGameOver();
	}

	@Override
	public void onRightBtn() throws NotEnoughResourcesException, UnallowedMovementException, ChampionDisarmedException, InvalidTargetException, AbilityUseException, CloneNotSupportedException {
		if ( oldBtn2 == marvelview.playview.moveBtn) {
			model.move(Direction.RIGHT);
			refresh();  }
		if ( oldBtn2 == marvelview.playview.attackBtn) {
			model.attack(Direction.RIGHT);
			refresh();  
			}
		if ( oldBtn2 == marvelview.playview.ability1Btn) {
			model.castAbility(model.getCurrentChampion().getAbilities().get(0)  , Direction.RIGHT);
			refresh();  
			}
		if ( oldBtn2 == marvelview.playview.ability2Btn) {
			model.castAbility(model.getCurrentChampion().getAbilities().get(1)  , Direction.RIGHT);
			refresh();  
			}
		if ( oldBtn2 == marvelview.playview.ability3Btn) {
			model.castAbility(model.getCurrentChampion().getAbilities().get(2)  , Direction.RIGHT);
			refresh();  
			}
		checkGameOver();
		
	}

	@Override
	public void onLeftBtn() throws NotEnoughResourcesException, UnallowedMovementException, ChampionDisarmedException, InvalidTargetException, AbilityUseException, CloneNotSupportedException {
		if ( oldBtn2 == marvelview.playview.moveBtn) {
			model.move(Direction.LEFT);
			refresh();  }
		if ( oldBtn2 == marvelview.playview.attackBtn) {
			model.attack(Direction.LEFT);
			refresh();  
			}
		if ( oldBtn2 == marvelview.playview.ability1Btn) {
			model.castAbility(model.getCurrentChampion().getAbilities().get(0)  , Direction.LEFT);
			refresh();  
			}
		if ( oldBtn2 == marvelview.playview.ability2Btn) {
			model.castAbility(model.getCurrentChampion().getAbilities().get(1)  , Direction.LEFT);
			refresh();  
			}
		if ( oldBtn2 == marvelview.playview.ability3Btn) {
			model.castAbility(model.getCurrentChampion().getAbilities().get(2)  , Direction.LEFT);
			refresh();  
			}
		checkGameOver();
	}
		
	
	public void checkGameOver() {
		
		if ( model.checkGameOver() == (Player)model.getFirstPlayer()) {
			
			String winner = model.getFirstPlayer().getName();
			marvelview.finalview = new FinalView (marvelview.frame, marvelview,winner);
		//	marvelview.finalview.winnerName.setText(model.getFirstPlayer().getName()  + " is the winner !!!");
			marvelview.frame.remove(marvelview.playview);
			marvelview.frame.add(marvelview.finalview);
			marvelview.frame.revalidate();
			marvelview.frame.repaint();
		}
		else if ( model.checkGameOver() == (Player)model.getSecondPlayer()) {
			String winner = model.getSecondPlayer().getName();
			marvelview.finalview = new FinalView (marvelview.frame, marvelview,winner);
			marvelview.frame.remove(marvelview.playview);
			marvelview.frame.add(marvelview.finalview);
			marvelview.frame.revalidate();
			marvelview.frame.repaint();
		}
		
		
		
	}
	}
	
	
	

