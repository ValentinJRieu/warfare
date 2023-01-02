package wargame.carte.types;

import wargame.carte.Terrain;

import java.awt.*;

public class Montagne extends Terrain {

	private static final Color color =  Color.ORANGE;

	public Montagne() {
		this.coutDeplacement = 4;
		this.bonusDefense = 4;
	}

	@Override
	public Color getImage() {
		return color;
	}
}
