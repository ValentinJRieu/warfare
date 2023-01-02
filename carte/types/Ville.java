package wargame.carte.types;

import wargame.carte.Terrain;

import java.awt.*;

public class Ville extends Terrain {
	private static final Color color = Color.BLUE;

	public Ville() {
		this.bonusDefense = 2;
		this.coutDeplacement = 1;
	}



	@Override
	public Color getImage() {
		return color;
	}
}
