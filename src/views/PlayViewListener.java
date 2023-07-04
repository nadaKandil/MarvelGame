package views;

import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;

public interface PlayViewListener {
	
	public void onMoveBtn();
	
	public void onAttackBtn();
	
	public void onAbility1Btn() throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException;
	
	public void onAbility2Btn() throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException;
	
	public void onAbility3Btn() throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException;
	
	public void onLeaderAbilityBtn() throws LeaderNotCurrentException, LeaderAbilityAlreadyUsedException;
	
	public void onEndTurnBtn();
	
	public void onUpBtn() throws NotEnoughResourcesException, UnallowedMovementException, ChampionDisarmedException, InvalidTargetException, CloneNotSupportedException, AbilityUseException;
	
	public void onDownBtn() throws NotEnoughResourcesException, UnallowedMovementException, ChampionDisarmedException, InvalidTargetException, AbilityUseException, CloneNotSupportedException;
	
	public void onRightBtn() throws NotEnoughResourcesException, UnallowedMovementException, ChampionDisarmedException, InvalidTargetException, AbilityUseException, CloneNotSupportedException;
	
	public void onLeftBtn() throws NotEnoughResourcesException, UnallowedMovementException, ChampionDisarmedException, InvalidTargetException, AbilityUseException, CloneNotSupportedException;

}
