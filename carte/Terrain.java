package wargame.carte;

import java.awt.*;

public abstract class Terrain {
	protected int coutDeplacement;
	protected int bonusDefense;
	 public int getCoutDeplacement() {return coutDeplacement;}

	public int getBonusDefense() {return bonusDefense;}

	public abstract Color getImage();

}
