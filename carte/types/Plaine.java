package wargame.carte.types;

import wargame.carte.Terrain;

import java.awt.*;
import java.io.Serializable;

public class Plaine extends Terrain implements Serializable {
	private static final Color color =  new Color(102,153,0);

	public Plaine() {
		this.coutDeplacement = 1;
		this.bonusDefense = 0;
	}

	@Override
	public Color getImage() {
		return color;
	}
}
