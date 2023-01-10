package wargame.carte.types;

import wargame.carte.Terrain;

import java.awt.*;
import java.io.Serializable;

public class Foret extends Terrain implements Serializable {
	private static final Color color =  new Color(0, 102, 0);

	public Foret() {
		this.coutDeplacement = 2;
		this.bonusDefense = 5;
	}

	@Override
	public Color getImage() {
		return color;
	}
}
