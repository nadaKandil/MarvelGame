package controller;

import java.io.IOException;

import engine.Game;
import engine.Player;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import views.Quiz;
import views.Quiz2;
import views.nextListener;

public class ControllerQuiz implements nextListener {

	
	public Game game ;
	public Quiz2 quizview;
	public int c;
	
	public ControllerQuiz () {
		quizview = new Quiz2();
		quizview.setListener(this);
	}
	
	@Override
	public void onNext() throws IOException {
		game = new Game(new Player("k"), new Player("r"));
		game.loadAbilities("Abilities.csv");
		
		quizview.c = (int) (Math.random() * 45);
		Ability a = game.getAvailableAbilities().get(quizview.c);
		
		quizview.abName.setText(a.getName());
		if ( a instanceof HealingAbility)
			quizview.abType.setText("Healing Ability");
		if ( a instanceof DamagingAbility)
			quizview.abType.setText("Damaging Ability");
		if ( a instanceof CrowdControlAbility)
			quizview.abType.setText("CrowdControl Ability");
		
		quizview.counter.setText(""+ quizview.c);
		
	}
	 
	public static void main(String[] args) {
		new ControllerQuiz();
	}
}
