package wargame.carte.types;

import wargame.carte.Terrain;

import java.awt.*;

public class Plaine extends Terrain {
	private static final Color color =  new Color(102,153,0);

	public Plaine() {
		this.coutDeplacement = 4;
		this.bonusDefense = 0;
	}

	@Override
	public Color getImage() {
		return color;
	}
}
