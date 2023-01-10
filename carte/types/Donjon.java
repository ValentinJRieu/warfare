package wargame.carte.types;

import java.awt.*;
import java.io.Serializable;

import wargame.carte.Terrain;

public class Donjon extends Terrain implements Serializable {

	private static final Color color = new Color(102,0,0);

	public Donjon() {
		this.bonusDefense = 2;
		this.coutDeplacement = 1;
	}

	@Override public Color getImage() {
		return color;
	}
}
