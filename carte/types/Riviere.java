package wargame.carte.types;

import wargame.carte.Terrain;

import java.awt.*;
import java.io.Serializable;

public class Riviere extends Terrain implements Serializable {
	private static final Color color =  new Color(102, 204,205);

	public Riviere() {
		this.coutDeplacement = 4;
		this.bonusDefense = 0;
	}

	@Override
	public Color getImage() {
		return color;
	}
}
