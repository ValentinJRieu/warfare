package wargame.carte.types;

import wargame.carte.Terrain;

import java.awt.*;

public class Riviere extends Terrain {
	private static final Color color =  new Color(0, 110,200);

	public Riviere() {
		this.coutDeplacement = 4;
		this.bonusDefense = 0;
	}

	@Override
	public Color getImage() {
		return color;
	}
}
