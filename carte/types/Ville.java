package wargame.carte.types;

import wargame.carte.Terrain;

import java.awt.*;
import java.io.Serializable;

public class Ville extends Terrain implements Serializable {
	private static final Color color = new Color(77,77,77);

	public Ville() {
		this.bonusDefense = 2;
		this.coutDeplacement = 1;
	}



	@Override
	public Color getImage() {
		return color;
	}
}
